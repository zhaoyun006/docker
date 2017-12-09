package com.cloud.sms.docker.common.session;

import org.apache.catalina.session.StandardSession;
import org.apache.catalina.Manager;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

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
public class RedisSession extends StandardSession {
    private static final long serialVersionUID = 1L;


    public RedisSession(Manager manager) {
        super(manager);
    }

    /**
     * Constructs a new {@link RedisSession} with no manager. Intended for deserialization usage.
     */
    protected RedisSession() {
        super(null);
    }

    public RedisSession(RedisSessionManager manager) {
        super(manager);
    }


    @Override
    public RedisSessionManager getManager() {
        return RedisSessionManager.class.cast(super.getManager());
    }

    @Override
    public void setId(String id) {
        // no super() invocation; don't want to remove/add the session
        this.id = id;
    }

    /**
     * Marks the session as dirty if an attribute changes and saves the session if so configured. {@inheritDoc}
     */
    @Override
    public void setAttribute(String key, Object value) {
        super.setAttribute(key, value);
    }

    /**
     * Marks the session as dirty when an attribute is removed and saves the session if so configured. {@inheritDoc}
     */
    @Override
    public void removeAttribute(String name) {
        super.removeAttribute(name);
    }

    /**
     * Performs post-deserialization logic.
     *
     * @param manager
     */
    public void postDeserialization(RedisSessionManager manager) {
        setManager(manager);
        if (listeners == null) {
            listeners = new ArrayList<>();
        }
        if (notes == null) {
            notes = new ConcurrentHashMap<>();
        }
    }
}
