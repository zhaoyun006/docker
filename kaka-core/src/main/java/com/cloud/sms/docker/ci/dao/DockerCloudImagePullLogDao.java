package com.cloud.sms.docker.ci.dao;
import com.cloud.sms.docker.common.dao.BaseDao;
import com.cloud.sms.docker.ci.entity.DockerCloudImagePullLogEntity;
import com.cloud.sms.docker.ci.dao.DockerCloudImagePullLogDao;
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
 * @date 2017-11-23 16:00:05
 * @since 1.0
 */
@Repository("com.cloud.sms.docker.ci.dao.DockerCloudImagePullLogDao")
public class DockerCloudImagePullLogDao extends BaseDao<DockerCloudImagePullLogEntity>{

    @Resource(name="docker.MybatisDaoContext")
     private MybatisDaoContext mybatisDaoContext;

     /**
     *
     * @param searchMap
     * @param pageBounds
     * @return
     */
     public PagingResult<DockerCloudImagePullLogEntity> findAll(SearchMap searchMap, PageBounds pageBounds, String sqlId){
        return mybatisDaoContext.findForPage(this.getClass().getName()+"."+sqlId,DockerCloudImagePullLogEntity.class,searchMap,pageBounds);
     }

     /**
     * 通用数据查询
     * @param searchMap
     * @param sqlId
     * @return
     */
    public List<DockerCloudImagePullLogEntity> getListData(SearchMap searchMap,  String sqlId){
        return mybatisDaoContext.findAll(this.getClass().getName()+"."+sqlId,DockerCloudImagePullLogEntity.class,searchMap);
    }}