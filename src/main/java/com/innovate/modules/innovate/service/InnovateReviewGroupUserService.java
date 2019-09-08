package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
public interface InnovateReviewGroupUserService extends IService<InnovateReviewGroupUserEntity> {
    List<InnovateReviewGroupUserEntity> queryAllGroupUser(Long groupId);
    /**
     * 查询评委组创建的用户ID列表
     */
    List<Long> queryUserIdList(Long groupId);


}
