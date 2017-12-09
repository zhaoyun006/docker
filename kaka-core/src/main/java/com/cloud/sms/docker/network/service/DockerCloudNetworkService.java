package com.cloud.sms.docker.network.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.network.entity.DockerCloudNetworkEntity;
import com.cloud.sms.docker.network.dao.DockerCloudNetworkDao;
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
 * @date 2017-10-19 08:49:19
 * @since 1.0
 */
@Service("com.cloud.sms.docker.network.service.DockerCloudNetworkService")
public class DockerCloudNetworkService extends BaseService<DockerCloudNetworkEntity,DockerCloudNetworkDao>{

    @Resource(name="com.cloud.sms.docker.network.dao.DockerCloudNetworkDao")
    private DockerCloudNetworkDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudNetworkEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudNetworkEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}