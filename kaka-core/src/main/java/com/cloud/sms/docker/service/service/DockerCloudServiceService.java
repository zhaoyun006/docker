package com.cloud.sms.docker.service.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.service.entity.DockerCloudServiceEntity;
import com.cloud.sms.docker.service.dao.DockerCloudServiceDao;
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
 * @date 2017-09-28 10:39:03
 * @since 1.0
 */
@Service("com.cloud.sms.docker.service.service.DockerCloudServiceService")
public class DockerCloudServiceService extends BaseService<DockerCloudServiceEntity,DockerCloudServiceDao>{

    @Resource(name="com.cloud.sms.docker.service.dao.DockerCloudServiceDao")
    private DockerCloudServiceDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudServiceEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudServiceEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}