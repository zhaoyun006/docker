package com.cloud.sms.docker.container.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.container.entity.DockerCloudContainerEntity;
import com.cloud.sms.docker.container.dao.DockerCloudContainerDao;
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
 * @date 2017-09-23 19:50:41
 * @since 1.0
 */
@Service("com.cloud.sms.docker.container.service.DockerCloudContainerService")
public class DockerCloudContainerService extends BaseService<DockerCloudContainerEntity,DockerCloudContainerDao>{

    @Resource(name="com.cloud.sms.docker.container.dao.DockerCloudContainerDao")
    private DockerCloudContainerDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudContainerEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudContainerEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}