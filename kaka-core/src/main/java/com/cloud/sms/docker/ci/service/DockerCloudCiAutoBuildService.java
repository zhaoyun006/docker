package com.cloud.sms.docker.ci.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.ci.entity.DockerCloudCiAutoBuildEntity;
import com.cloud.sms.docker.ci.dao.DockerCloudCiAutoBuildDao;
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
 * @date 2017-10-26 10:24:46
 * @since 1.0
 */
@Service("com.cloud.sms.docker.ci.service.DockerCloudCiAutoBuildService")
public class DockerCloudCiAutoBuildService extends BaseService<DockerCloudCiAutoBuildEntity,DockerCloudCiAutoBuildDao>{

    @Resource(name="com.cloud.sms.docker.ci.dao.DockerCloudCiAutoBuildDao")
    private DockerCloudCiAutoBuildDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudCiAutoBuildEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudCiAutoBuildEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}