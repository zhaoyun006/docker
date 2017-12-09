package com.cloud.sms.docker.authority.service;
import com.cloud.sms.docker.common.service.BaseService;
import com.cloud.sms.docker.authority.entity.DockerCloudAuthorityUserEntity;
import com.cloud.sms.docker.authority.dao.DockerCloudAuthorityUserDao;
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
 * @date 2017-09-22 09:56:03
 * @since 1.0
 */
@Service("com.cloud.sms.docker.authority.service.DockerCloudAuthorityUserService")
public class DockerCloudAuthorityUserService extends BaseService<DockerCloudAuthorityUserEntity,DockerCloudAuthorityUserDao> {

    @Resource(name="com.cloud.sms.docker.authority.dao.DockerCloudAuthorityUserDao")
    private DockerCloudAuthorityUserDao dao;

    /**
     * @param searchMap
     * @param pageBounds
     * @return
     */
    public PagingResult<DockerCloudAuthorityUserEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId) {
        return dao.findAll(searchMap, pageBounds, sqlId);
    }

         /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudAuthorityUserEntity> getListData(SearchMap searchMap, String sqlId){
        return dao.getListData(searchMap, sqlId);
    }

}