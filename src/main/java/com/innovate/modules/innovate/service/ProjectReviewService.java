package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.ProjectReviewEntity;
import com.innovate.modules.innovate.entity.ProjectStaffInfoEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:42
 * @Version 1.0
 */
public interface ProjectReviewService extends IService<ProjectReviewEntity> {

    //查询分数
    ProjectReviewEntity queryScore(Map<String,Object> params);
    //评分
    @Transactional
    void score(ProjectReviewEntity projectReviewEntity);
    //绑定用户
    @Transactional
    void reviewUser(Map<String,Object> params);

    List<ProjectReviewEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);


    /**
     * 统计未评分的个数
     */
    Long queryCount(Map<String,Object> params);

    /**
     * 计算平均分
     */
    Double queryScoreAvg(Map<String,Object> params);
}
