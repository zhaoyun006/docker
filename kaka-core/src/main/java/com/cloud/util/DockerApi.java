package com.cloud.util;

/**
 * Created by zhaoyun on 2017/9/25.
 */
public class DockerApi {



    /**
     *
     * @param key
     * @return
     */
    public static String dockerInfo(String key){
        try {
            String result = HttpUtil.sendGet("http://10.16.55.101:2375/" + key);
            System.out.println(result);
            return result;
        }catch (Exception e){
            return null;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public static String dockerCreate(String key, String data){
        try {
            String result =  HttpUtil.httpPostJson("http://10.16.55.101:2375/"+key, data, "POST");
            System.out.println(result);
            return result;
        }catch (Exception e){
            return null;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public static String dockerDelete(String key, String data){
        try {
            String result =  HttpUtil.httpPostJson("http://10.16.55.101:2375/"+key, data, "DELETE");
            System.out.println(result);
            return result;
        }catch (Exception e){
            return null;
        }
    }

}
