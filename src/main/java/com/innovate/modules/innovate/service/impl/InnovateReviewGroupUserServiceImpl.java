package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.InnovateReviewGroupUserDao;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;
import com.innovate.modules.innovate.service.InnovateReviewGroupUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Service
public class InnovateReviewGroupUserServiceImpl extends ServiceImpl<InnovateReviewGroupUserDao, InnovateReviewGroupUserEntity> implements InnovateReviewGroupUserService {

    @Override
    public List<InnovateReviewGroupUserEntity> queryAllGroupUser(Long groupId) {
        return baseMapper.queryAllByGroupId(groupId);
    }

    @Override
    public List<Long> queryUserIdList(Long groupId) {
        return baseMapper.queryUserIdList(groupId);
    }
}
