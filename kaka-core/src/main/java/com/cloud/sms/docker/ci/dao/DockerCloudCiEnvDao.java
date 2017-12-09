package com.cloud.sms.docker.ci.dao;
import com.cloud.sms.docker.common.dao.BaseDao;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.ci.dao.DockerCloudCiEnvDao;
import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import java.util.List;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.asura.framework.dao.mybatis.base.MybatisDaoContext;
import org.springframework.stereotype.Repository;

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
@Repository("com.cloud.sms.docker.ci.dao.DockerCloudCiEnvDao")
public class DockerCloudCiEnvDao extends BaseDao<DockerCloudCiEnvEntity>{

    @Resource(name="docker.MybatisDaoContext")
     private MybatisDaoContext mybatisDaoContext;

     /**
     *
     * @param searchMap
     * @param pageBounds
     * @return
     */
     public PagingResult<DockerCloudCiEnvEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId){
        return mybatisDaoContext.findForPage(this.getClass().getName()+"."+sqlId,DockerCloudCiEnvEntity.class,searchMap,pageBounds);
     }

     /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudCiEnvEntity> getListData(SearchMap searchMap,  String sqlId){
        return mybatisDaoContext.findAll(this.getClass().getName()+"."+sqlId,DockerCloudCiEnvEntity.class,searchMap);
    }}