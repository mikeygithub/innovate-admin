package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.InnovateReviewGroupEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
public interface InnovateReviewGroupService extends IService<InnovateReviewGroupEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<InnovateReviewGroupEntity> queryAllGroup();
    InnovateReviewGroupEntity queryById(Long groupId);
    @Transactional
    void save(InnovateReviewGroupEntity innovateReviewGroupEntity);
    @Transactional
    void update(InnovateReviewGroupEntity innovateReviewGroupEntity);
    InnovateReviewGroupEntity insertEntity(InnovateReviewGroupEntity innovateReviewGroupEntity);
}
