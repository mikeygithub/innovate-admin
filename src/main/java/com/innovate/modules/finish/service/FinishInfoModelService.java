package com.innovate.modules.finish.service;

import com.innovate.common.utils.PageUtils;
import com.innovate.modules.finish.entity.FinishInfoModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 * 数据操作service
 **/
public interface FinishInfoModelService {

//    项目分页查询
    PageUtils queryPage(Map<String, Object> params);

//    项目不分页查询
    List<FinishInfoModel> queryAll(Map<String, Object> params);

//    条件查询
    FinishInfoModel query(Map<String, Object> params);

//    保存项目
    @Transactional
    void saveEntity(FinishInfoModel finishInfoModel);

//    更新项目
    @Transactional
    void updateEntity(FinishInfoModel finishInfoModel);

//    保存或更新项目属性
    @Transactional
    void saveOrupdateProps(FinishInfoModel finishInfoModel);

//    删除项目
    @Transactional
    void deleteEntity(Map<String, Object> params);

//    删除项目属性
    @Transactional
    void deleteProps(Map<String, Object> params);
}
