package com.cloud.sms.docker.ci.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvPermissionsEntity;
import com.cloud.sms.docker.ci.dao.DockerCloudCiEnvPermissionsDao;
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
 * @date 2017-10-23 14:10:34
 * @since 1.0
 */
@Service("com.cloud.sms.docker.ci.service.DockerCloudCiEnvPermissionsService")
public class DockerCloudCiEnvPermissionsService extends BaseService<DockerCloudCiEnvPermissionsEntity,DockerCloudCiEnvPermissionsDao>{

    @Resource(name="com.cloud.sms.docker.ci.dao.DockerCloudCiEnvPermissionsDao")
    private DockerCloudCiEnvPermissionsDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudCiEnvPermissionsEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudCiEnvPermissionsEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}