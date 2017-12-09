package com.cloud.sms.docker.log.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.log.entity.DockerCloudLogEntity;
import com.cloud.sms.docker.log.dao.DockerCloudLogDao;
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
 * @date 2017-09-23 18:21:17
 * @since 1.0
 */
@Service("com.cloud.sms.docker.log.service.DockerCloudLogService")
public class DockerCloudLogService extends BaseService<DockerCloudLogEntity,DockerCloudLogDao>{

    @Resource(name="com.cloud.sms.docker.log.dao.DockerCloudLogDao")
    private DockerCloudLogDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudLogEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudLogEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}