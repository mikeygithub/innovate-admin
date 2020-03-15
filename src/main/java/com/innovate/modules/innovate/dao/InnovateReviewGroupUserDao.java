package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Mapper
public interface InnovateReviewGroupUserDao extends BaseMapper<InnovateReviewGroupUserEntity> {
    List<InnovateReviewGroupUserEntity> queryAllByGroupId(Long groupId);
    void deleteByGroupId(Long groupId);
    /**
     * 查询评委组创建的用户ID列表
     */
    List<Long> queryUserIdList(Long groupId);
}
