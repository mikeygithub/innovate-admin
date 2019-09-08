package com.innovate.modules.innovate.service;

import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.ProjectInfoModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 * 数据操作service
 **/
public interface ProjectInfoModelService {

//    项目分页查询
    PageUtils queryPage(Map<String, Object> params);

//    项目不分页查询
    List<ProjectInfoModel> queryAll(Map<String, Object> params);

//    条件查询
    ProjectInfoModel query(Map<String, Object> params);

//    保存项目
    @Transactional
    void saveEntity(ProjectInfoModel projectInfoModel);

//    更新项目
    @Transactional
    void updateEntity(ProjectInfoModel projectInfoModel);

//    保存或更新项目属性
    @Transactional
    void saveOrupdateProps (ProjectInfoModel projectInfoModel);

//    删除项目
    @Transactional
    void deleteEntity(Map<String, Object> params);

//    删除项目属性
    @Transactional
    void deleteProps(Map<String, Object> params);
}
