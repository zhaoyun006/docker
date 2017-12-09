package com.cloud.sms.docker.images.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.images.entity.DockerCloudImagesEntity;
import com.cloud.sms.docker.images.dao.DockerCloudImagesDao;
import org.springframework.stereotype.Service;
import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;import java.util.List;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;

import javax.annotation.Resource;



/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>
 * <BR>-----------------------------------------------
 * <BR>
 * </PRE>
 *
 * @author zhaozq14
 * @version 1.0
 * @date 2017-10-13 14:16:48
 * @since 1.0
 */
@Service("com.cloud.sms.docker.images.service.DockerCloudImagesService")
public class DockerCloudImagesService extends BaseService<DockerCloudImagesEntity,DockerCloudImagesDao>{

    @Resource(name="com.cloud.sms.docker.images.dao.DockerCloudImagesDao")
    private DockerCloudImagesDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudImagesEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudImagesEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}