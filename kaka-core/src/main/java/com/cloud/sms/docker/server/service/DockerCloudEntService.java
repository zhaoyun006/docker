package com.cloud.sms.docker.server.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.server.entity.DockerCloudEntEntity;
import com.cloud.sms.docker.server.dao.DockerCloudEntDao;
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
 * @date 2017-11-08 17:22:02
 * @since 1.0
 */
@Service("com.cloud.sms.docker.server.service.DockerCloudEntService")
public class DockerCloudEntService extends BaseService<DockerCloudEntEntity,DockerCloudEntDao>{

    @Resource(name="com.cloud.sms.docker.server.dao.DockerCloudEntDao")
    private DockerCloudEntDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudEntEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudEntEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}