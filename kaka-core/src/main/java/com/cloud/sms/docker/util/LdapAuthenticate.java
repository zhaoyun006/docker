package com.cloud.sms.docker.util;

import com.novell.ldap.*;
import com.cloud.sms.docker.common.entity.LdapEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zy
 * @Date 2016-06-21 ldap登陆验证
 */
@Component
public class LdapAuthenticate {

    @Resource(name = "ldapConfig")
    private LdapEntity ldapDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapAuthenticate.class);

    /**
     * 连接LDAP
     *
     * @throws LDAPException
     */
    @SuppressWarnings("deprecation")
    public boolean connetLDAP(String username, String password)
            throws LDAPException {
        LDAPConnection ldapConnection = new LDAPConnection();
        // 连接Ldap需要的信息
        ldapConnection.connect(ldapDao.getLdapServer(), ldapDao.getLdapPort());
        try {
            ldapConnection.bind(ldapDao.getLdapPrefix() + username , password);
            boolean result = ldapConnection.isBound();
            ldapConnection.disconnect();
            return result;
        } catch (Exception e) {
            ldapConnection.disconnect();
            LOGGER.error("connect error : {}", e);
            return false;
        }

    }


    /**
     * @param keys
     * @param username
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public Map ldapSearch(String[] keys, String username) {
        LDAPConnection lc = new LDAPConnection();
        Map map = new HashMap<>(100);
        try {
            lc.connect(ldapDao.getLdapServer(), ldapDao.getLdapPort());
            lc.bind(3, ldapDao.getLdapPrefix() + ldapDao.getLdapUsername(), ldapDao.getLdapPassword().getBytes("UTF8"));
            String searchBase = ldapDao.getLdapSearchBase();
            String searchFilter = username;
            int searchScope = 2;
            LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, keys, false);
            //LDAPSearchResults 实现了 collection 接口
            while (searchResults.hasMore()) {
                //结果集中每个内容都是一个 LDAPEntry 对象
                LDAPEntry le = searchResults.next();
                System.out.println(le.getDN());
                //通过 LDAPEntry 对象来获取 LDAPAttributeSet 对象
                LDAPAttributeSet attributeSet = le.getAttributeSet();
                Set sortedAttributes = new TreeSet(attributeSet);
                Iterator allAttributes = sortedAttributes.iterator();

                while (allAttributes.hasNext()) {
                    LDAPAttribute attribute = (LDAPAttribute) allAttributes.next();
                    //获取参数名
                    String attributeName = attribute.getName();
                    System.out.println("\t\t" + attributeName);
                    //其参数值可以为多个，利用Enumeration 列出全部该属性的值
                    Enumeration allValues = attribute.getStringValues();
                    if (allValues != null) {
                        while (allValues.hasMoreElements()) {
                            String value = (String) allValues.nextElement();
                            System.out.println("\t\t\t" + value);
                            for (String key : keys) {
                                if (key.equals(attributeName)) {
                                    map.put(attributeName, value);
                                }
                            }
                        }
                    }
                }

            }
            lc.disconnect();
        } catch (Exception e) {
            LOGGER.error("ldap错误 : {}", e);
        }
        return map;
    }

    /**
     * @param keys
     * @param username
     * @return
     */
    public List ldapSearchList(String[] keys, String username) {
        LDAPConnection lc = new LDAPConnection();
        List<Map> list = new ArrayList<>();

        try {
            lc.connect(ldapDao.getLdapServer(), ldapDao.getLdapPort());
            lc.bind(3, ldapDao.getLdapPrefix() + ldapDao.getLdapUsername(), ldapDao.getLdapPassword().getBytes("UTF8"));
            String searchBase = ldapDao.getLdapSearchBase();
            String searchFilter = username;
            int searchScope = 2;
            LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, keys, false);
            //LDAPSearchResults 实现了 collection 接口
            while (searchResults.hasMore()) {
                //结果集中每个内容都是一个 LDAPEntry 对象
                LDAPEntry le = searchResults.next();
                System.out.println(le.getDN());
                //通过 LDAPEntry 对象来获取 LDAPAttributeSet 对象
                LDAPAttributeSet attributeSet = le.getAttributeSet();
                Set sortedAttributes = new TreeSet(attributeSet);
                Iterator allAttributes = sortedAttributes.iterator();

                while (allAttributes.hasNext()) {
                    Map map = new HashMap<>(100);
                    LDAPAttribute attribute = (LDAPAttribute) allAttributes.next();
                    //获取参数名
                    String attributeName = attribute.getName();
                    System.out.println("\t\t" + attributeName);
                    //其参数值可以为多个，利用Enumeration 列出全部该属性的值
                    Enumeration allValues = attribute.getStringValues();
                    if (allValues != null) {
                        while (allValues.hasMoreElements()) {
                            String value = (String) allValues.nextElement();
                            System.out.println("\t\t\t" + value);
                            map.put(attributeName, value);
                            list.add(map);
                        }
                    }
                }

            }
            lc.disconnect();
        } catch (Exception e) {
            LOGGER.error("ldap错误 : {}", e);
        }
        return list;
    }

    /**
     * 获取ldap返回数据的值
     *
     * @param map
     * @param key
     * @return
     */
    public String getMap(Map<String, String> map, String key) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * @param key
     * @param user
     * @return
     */
    public String getSignUserInfo(String key, String user) {
        Map map = ldapSearch(new String[]{key}, user);
        return getMap(map, key);
    }

}