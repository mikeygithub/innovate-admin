package com.innovate.modules.declare.service;

import com.innovate.common.utils.PageUtils;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.entity.DeclareInfoModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 * 数据操作service
 **/
public interface DeclareInfoModelService {

//    项目分页查询
    PageUtils queryPage(Map<String, Object> params);

//    项目不分页查询
    List<DeclareInfoModel> queryAll(Map<String, Object> params);

//    条件查询
    DeclareInfoModel query(Map<String, Object> params);

//    保存项目
    @Transactional
    void saveEntity(DeclareInfoModel declareInfoModel);

//    更新项目
    @Transactional
    void updateEntity(DeclareInfoModel declareInfoModel);

//    保存或更新项目属性
    @Transactional
    void saveOrupdateProps(DeclareInfoModel declareInfoModel);

//    删除项目
    @Transactional
    void deleteEntity(Map<String, Object> params);

//    删除项目属性
    @Transactional
    void deleteProps(Map<String, Object> params);

    /**
     * 查询可以结题的项目
     * @param params
     * @return
     */
    List<DeclareInfoEntity> queryCanFinish(Map<String, Object> params);
}
