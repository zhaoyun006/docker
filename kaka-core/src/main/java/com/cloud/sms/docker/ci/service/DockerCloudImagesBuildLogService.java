package com.cloud.sms.docker.ci.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.ci.entity.DockerCloudImagesBuildLogEntity;
import com.cloud.sms.docker.ci.dao.DockerCloudImagesBuildLogDao;
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
 * @date 2017-10-18 09:52:19
 * @since 1.0
 */
@Service("com.cloud.sms.docker.ci.service.DockerCloudImagesBuildLogService")
public class DockerCloudImagesBuildLogService extends BaseService<DockerCloudImagesBuildLogEntity,DockerCloudImagesBuildLogDao>{

    @Resource(name="com.cloud.sms.docker.ci.dao.DockerCloudImagesBuildLogDao")
    private DockerCloudImagesBuildLogDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudImagesBuildLogEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId selectServerApi
     * @return
     */
    public List<DockerCloudImagesBuildLogEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }

    /**
     *
     */
    public DockerCloudImagesBuildLogEntity getBuildLog(SearchMap searchMap, String sqlId){
        return dao.getBuildLog(searchMap, sqlId);
    }
}