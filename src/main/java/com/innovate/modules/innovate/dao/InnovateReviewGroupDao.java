package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.InnovateReviewGroupEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/7
 **/
@Mapper
public interface InnovateReviewGroupDao extends BaseMapper<InnovateReviewGroupEntity> {
    List<InnovateReviewGroupEntity> queryAll();
    InnovateReviewGroupEntity save(InnovateReviewGroupEntity innovateGroupEntity);
}
