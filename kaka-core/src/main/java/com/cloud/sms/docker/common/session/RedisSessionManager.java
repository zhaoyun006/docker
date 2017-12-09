package com.cloud.sms.docker.common.session;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.catalina.session.ManagerBase;

import java.io.IOException;
import java.util.Enumeration;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
public abstract class RedisSessionManager extends ManagerBase {
    protected final Logger log = LoggerFactory.getLogger(RedisSessionManager.class);

    /**
     * Default value to prefix redis keys with.
     */
    public static final String DEFAULT_SESSION_KEY_PREFIX = "_docker_cloud_";
    /**
     * in minutes
     */
    private int sessionExpirationTime;
    /**
     * access should be done via #getClient()
     */
    private RedisSessionClient _client;
    /**
     * session
     */
    private String sessionKeyPrefix = DEFAULT_SESSION_KEY_PREFIX;


    private ThreadLocal<RedisSession> currentSession = new ThreadLocal<>();

    protected RedisSessionClient getClient() {
        return _client;
    }

    /**
     * Construct the {@link RedisSessionClient}
     *
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    protected abstract RedisSessionClient buildClient() throws ClassNotFoundException, InstantiationException, IllegalAccessException, LifecycleException;

    /**
     * Get the encoded size of the object
     *
     * @param obj
     * @return
     */
    int getEncodedSize(Object obj) {
        return getClient().getEncodedSize(obj);
    }

    /**
     * Obtain the {@link ClassLoader} for this context. Necessary for deserialization of {@link RedisSession}s
     *
     * @return
     */
    public ClassLoader getContainerClassLoader() {
        return getContext().getLoader().getClassLoader();
    }

    @Override
    public void load() throws ClassNotFoundException, IOException {
        // noop
    }

    @Override
    public void unload() throws IOException {
        // noop
    }

    @Override
    protected synchronized void startInternal() throws LifecycleException {
        super.startInternal();
        try {
            this._client = buildClient();
        } catch (Throwable t) {
            log.error("Unable to load serializer", t);
            throw new LifecycleException(t);
        }
        this.sessionExpirationTime = getContext().getSessionTimeout();
        if (this.sessionExpirationTime < 0) {
            log.warn("Ignoring negative session expiration time");
            this.sessionExpirationTime = 0;
        }
        log.info("Will expire sessions after " + sessionExpirationTime + " minutes");
        RedisSessionRequestValue value = new RedisSessionRequestValue(this);
        this.getContext().getPipeline().addValve(value);
        setState(LifecycleState.STARTING);
    }

    @Override
    protected synchronized void stopInternal() throws LifecycleException {
        super.stopInternal();
        log.info("Stopping...");
        getClient().shutdown();
        setState(LifecycleState.STOPPING);
    }

    @Override
    public Session createSession(String requestedSessionId) {
        RedisSession session = createEmptySession();
        session.setNew(true);
        session.setValid(true);
        session.setCreationTime(System.currentTimeMillis());
        session.setMaxInactiveInterval(sessionExpirationTime * 60);
        session.setId(requestedSessionId == null ? generateSessionId() : requestedSessionId);
        session.tellNew();
        save(session, true);
        return session;
    }

    @Override
    protected String generateSessionId() {
        String sessionId = null;
        while (sessionId == null) {
            sessionId = prefixJvmRoute(super.generateSessionId());
            if (getClient().exists(generateRedisSessionKey(sessionId))) {
                log.debug("Rejecting duplicate sessionId: " + sessionId);
                sessionId = null;
            } else {
                log.debug("Generated new sessionId: " + sessionId);
            }
        }
        return sessionId;
    }

    /**
     * Generate the storage key for the given sessionId
     *
     * @param sessionId
     * @return
     */
    private String generateRedisSessionKey(final String sessionId) {
        if (sessionId == null) {
            throw new IllegalArgumentException("sessionId must not be null");
        }
        String sessionKey = sessionId;
        if (!sessionKey.startsWith(sessionKeyPrefix)) {
            sessionKey = sessionKeyPrefix + sessionKey;
        }
        return sessionKey;
    }

    /**
     * Prefix the given sessionId with the JVM Route
     *
     * @param sessionId
     * @return
     */
    private String prefixJvmRoute(String sessionId) {
        String jvmRoute = getJvmRoute();
        if (jvmRoute != null) {
            String jvmRoutePrefix = '.' + jvmRoute;
            return sessionId.endsWith(jvmRoutePrefix) ? sessionId : sessionId + jvmRoutePrefix;
        } else {
            return sessionId;
        }
    }

    @Override
    public RedisSession createEmptySession() {
        return new RedisSession(this);
    }

    @Override
    public void add(Session session) {
        if (RedisSession.class.isAssignableFrom(session.getClass())) {
            save(RedisSession.class.cast(session), false);
        } else {
            throw new UnsupportedOperationException("Could not add a session with class " + session.getClass());
        }
    }

    @Override
    public Session findSession(String id) throws IOException{
        RedisSession session = null;
        log.debug("Finding session " + id);
        log.debug("Loading from redis");
        try {
            session = getClient().load(generateRedisSessionKey(id));
        } catch (Throwable t) {
            try {
                this._client = buildClient();
                log.error("重建session ");
            }catch (Exception e){
                e.printStackTrace();
            }

            log.error("Failed to load session [" + id + "] from redis", t);
        }
        if (session != null) {
            log.debug("Found session " + id + " in redis");
            session.postDeserialization(this);
            // Fix issue #12
            session.setNew(false);
        }
        currentSession.set(session);
        return session;
    }

    public void save(RedisSession redisSession, boolean forceSave) {
        log.debug("Checking if session " + redisSession.getId() + " needs to be saved in redis");
        if (log.isTraceEnabled()) {
            log.trace("Session Contents [" + redisSession.getId() + "]:");
            Enumeration<String> en = redisSession.getAttributeNames();
            while (en.hasMoreElements()) {
                String e = en.nextElement();
                log.trace("  " + e + ": " + String.valueOf(redisSession.getAttribute(e)));
            }
        }
        final String sessionKey = generateRedisSessionKey(redisSession.getId());
        if (forceSave) {
            log.debug("Saving " + redisSession.getId() + " to redis");
            log.trace("Setting expire on " + redisSession.getId() + " to " + sessionExpirationTime);
            try {
                getClient().save(sessionKey, redisSession, sessionExpirationTime);
            } catch (Throwable t) {
                log.error("Failed to save session [" + redisSession.getId() + "]", t);
            }
        } else {
            log.debug("Not saving " + redisSession.getId() + " to redis");
        }
        currentSession.set(redisSession);
    }

    @Override
    public void remove(Session session, boolean update) {
        log.debug("Removing session ID : " + session.getId());
        try {
            getClient().delete(generateRedisSessionKey(session.getId()));
        } catch (Throwable t) {
            log.error("Failed to remove session [" + session.getId() + "]", t);
        }
    }

    /**
     * 确保session会被持久化到redis
     */
    public void afterRequest() {
        try {
            RedisSession session = currentSession.get();
            if (session == null) {
                return;
            }
            if (session.isValid()) {
                save(session, true);
            } else {
                remove(session);
            }
        } finally {
            currentSession.remove();
        }
    }

    @Override
    public void processExpires() {
        // Redis will handle expiration
    }

    /**
     * Define the prefix for all redis keys.<br>
     * Defaults to {@value #DEFAULT_SESSION_KEY_PREFIX}
     *
     * @param sessionKeyPrefix
     */
    public void setSessionKeyPrefix(String sessionKeyPrefix) {
        this.sessionKeyPrefix = sessionKeyPrefix;
    }

    public int getSessionExpirationTime() {
        return sessionExpirationTime;
    }

    public void setSessionExpirationTime(int sessionExpirationTime) {
        this.sessionExpirationTime = sessionExpirationTime;
    }
}
