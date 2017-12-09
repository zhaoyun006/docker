package com.cloud.sms.docker.server.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.server.entity.DockerCloudGroupsEntity;
import com.cloud.sms.docker.server.dao.DockerCloudGroupsDao;
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
 * @date 2017-10-11 11:25:10
 * @since 1.0
 */
@Service("com.cloud.sms.docker.server.service.DockerCloudGroupsService")
public class DockerCloudGroupsService extends BaseService<DockerCloudGroupsEntity,DockerCloudGroupsDao>{

    @Resource(name="com.cloud.sms.docker.server.dao.DockerCloudGroupsDao")
    private DockerCloudGroupsDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudGroupsEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudGroupsEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}