package com.cloud.sms.docker.app.service;
import com.cloud.sms.docker.app.entity.DockerCloudAppEntity;
import com.cloud.sms.docker.app.dao.DockerCloudAppDao;
import com.cloud.sms.docker.common.service.BaseService;
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
 * @date 2017-09-22 21:53:23
 * @since 1.0
 */
@Service("com.cloud.sms.docker.app.service.DockerCloudAppService")
public class DockerCloudAppService extends BaseService<DockerCloudAppEntity,DockerCloudAppDao> {

    @Resource(name="com.cloud.sms.docker.app.dao.DockerCloudAppDao")
    private DockerCloudAppDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudAppEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudAppEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}