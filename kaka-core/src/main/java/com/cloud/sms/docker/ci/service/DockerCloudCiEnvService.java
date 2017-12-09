package com.cloud.sms.docker.ci.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.ci.dao.DockerCloudCiEnvDao;
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
 * @date 2017-10-14 14:01:49
 * @since 1.0
 */
@Service("com.cloud.sms.docker.ci.service.DockerCloudCiEnvService")
public class DockerCloudCiEnvService extends BaseService<DockerCloudCiEnvEntity,DockerCloudCiEnvDao>{

    @Resource(name="com.cloud.sms.docker.ci.dao.DockerCloudCiEnvDao")
    private DockerCloudCiEnvDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudCiEnvEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudCiEnvEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }}