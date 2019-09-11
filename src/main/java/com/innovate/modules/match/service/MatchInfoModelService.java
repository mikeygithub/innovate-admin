package com.innovate.modules.match.service;

import com.innovate.common.utils.PageUtils;
import com.innovate.modules.match.entity.MatchInfoModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 * 数据操作service
 **/
public interface MatchInfoModelService {

//    项目分页查询
    PageUtils queryPage(Map<String, Object> params);

//    项目不分页查询
    List<MatchInfoModel> queryAll(Map<String, Object> params);

//    条件查询
    MatchInfoModel query(Map<String, Object> params);

//    保存项目
    @Transactional
    void saveEntity(MatchInfoModel matchInfoModel);

    //获奖情况
    void saveAwardEntity(MatchInfoModel matchInfoModel);

//    更新项目
    @Transactional
    void updateEntity(MatchInfoModel matchInfoModel);

//    保存或更新项目属性
    @Transactional
    void saveOrupdateProps(MatchInfoModel matchInfoModel);

//    删除项目
    @Transactional
    void deleteEntity(Map<String, Object> params);

//    删除项目属性
    @Transactional
    void deleteProps(Map<String, Object> params);

}
