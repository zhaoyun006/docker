package com.cloud.sms.docker.log.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.log.entity.DockerCloudOperLogEntity;
import com.cloud.sms.docker.log.dao.DockerCloudOperLogDao;
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
 * @date 2017-10-12 10:26:27
 * @since 1.0
 */
@Service("com.cloud.sms.docker.log.service.DockerCloudOperLogService")
public class DockerCloudOperLogService extends BaseService<DockerCloudOperLogEntity,DockerCloudOperLogDao>{

    @Resource(name="com.cloud.sms.docker.log.dao.DockerCloudOperLogDao")
    private DockerCloudOperLogDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudOperLogEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudOperLogEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}