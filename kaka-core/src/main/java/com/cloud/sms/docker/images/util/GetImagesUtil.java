package com.cloud.sms.docker.images.util;

import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.util.HttpUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/10/11.
 */
public class GetImagesUtil {

    private static Logger logger = LoggerFactory.getLogger(GetImagesUtil.class);

    /**
     *
     * @param domain
     * @return
     */
   public static Map<String, ArrayList> getImages(String domain){
        try {
            String imagesNumber = HttpUtil.sendHttpsRequest("https://" + domain + "/v2/_catalog", "", "GET");
            if (CheckUtil.checkString(imagesNumber)) {
                Map<String, ArrayList> data = new Gson().fromJson(imagesNumber, Map.class);
                return data;
            }
        }catch (Exception e){
            logger.error("访问仓库失败:", e);
        }
        return new HashMap<>();
    }

    /**
     *
     * @param domain
     * @return
     */
    public static int getRegistryImagesNumber(String ip, String domain){
        if (!CheckUtil.checkString(domain)){
            domain = ip;
        }
        Map<String, ArrayList> data = getImages(domain);
        if (null != data){
         return 0;
        }else {
            return data.get("repositories").size();
        }
    }
}
