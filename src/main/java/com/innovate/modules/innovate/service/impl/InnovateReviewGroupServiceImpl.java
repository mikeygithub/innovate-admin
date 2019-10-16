package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.InnovateReviewGroupDao;
import com.innovate.modules.innovate.entity.InnovateReviewGroupEntity;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;
import com.innovate.modules.innovate.service.InnovateReviewGroupService;
import com.innovate.modules.innovate.service.InnovateReviewGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Service
public class InnovateReviewGroupServiceImpl extends ServiceImpl<InnovateReviewGroupDao, InnovateReviewGroupEntity> implements InnovateReviewGroupService {

    @Autowired
    private InnovateReviewGroupUserService innovateReviewGroupUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = params.get("key").toString();

        EntityWrapper<InnovateReviewGroupEntity> ew = new EntityWrapper<>();

        if (key!=null&&key!="")ew.like("group_name",key, SqlLike.DEFAULT);

        Page<InnovateReviewGroupEntity> page = this.selectPage(
                new Query<InnovateReviewGroupEntity>(params).getPage(),
                ew
        );
        return new PageUtils(page);
    }

    @Override
    public List<InnovateReviewGroupEntity> queryAllGroup() {
        List<InnovateReviewGroupEntity> tempInnovateReviewGroupEntities = baseMapper.queryAll();
        List<InnovateReviewGroupEntity> innovateReviewGroupEntities = new ArrayList<>();
        for (InnovateReviewGroupEntity innovateReviewGroupEntity: tempInnovateReviewGroupEntities) {
            List<InnovateReviewGroupUserEntity> innovateReviewGroupUserEntities = innovateReviewGroupUserService.queryAllGroupUser(innovateReviewGroupEntity.getGroupId());
            innovateReviewGroupEntity.setInnovateReviewGroupUserEntities(innovateReviewGroupUserEntities);
            innovateReviewGroupEntities.add(innovateReviewGroupEntity);
        }
        return innovateReviewGroupEntities;
    }

    @Override
    public InnovateReviewGroupEntity queryById(Long groupId) {
        InnovateReviewGroupEntity innovateGroupEntity = baseMapper.selectById(groupId);
        List<InnovateReviewGroupUserEntity> innovateGroupUserEntities = innovateReviewGroupUserService.queryAllGroupUser(groupId);
        innovateGroupEntity.setInnovateReviewGroupUserEntities(innovateGroupUserEntities);
        return innovateGroupEntity;
    }

    @Override
    public void save(InnovateReviewGroupEntity innovateGroupEntity) {
        List<InnovateReviewGroupUserEntity> innovateReviewGroupUserEntities = innovateGroupEntity.getInnovateReviewGroupUserEntities();
        innovateGroupEntity.setInnovateReviewGroupUserEntities(null);
        baseMapper.insert(innovateGroupEntity);
        innovateReviewGroupUserService.deleteById(innovateGroupEntity.getGroupId());
        for (InnovateReviewGroupUserEntity innovateReviewGroupUserEntity: innovateReviewGroupUserEntities) {
            innovateReviewGroupUserEntity.setGroupId(innovateGroupEntity.getGroupId());
            innovateReviewGroupUserService.insert(innovateReviewGroupUserEntity);
        }
    }

    @Override
    public void update(InnovateReviewGroupEntity innovateReviewGroupEntity) {
        List<InnovateReviewGroupUserEntity> innovateGroupUserEntities = innovateReviewGroupEntity.getInnovateReviewGroupUserEntities();
        innovateReviewGroupEntity.setInnovateReviewGroupUserEntities(null);
        baseMapper.updateById(innovateReviewGroupEntity);
        innovateReviewGroupUserService.deleteById(innovateReviewGroupEntity.getGroupId());
        for (InnovateReviewGroupUserEntity innovateReviewGroupUserEntity: innovateGroupUserEntities) {
            innovateReviewGroupUserService.insert(innovateReviewGroupUserEntity);
        }
    }

    @Override
    public InnovateReviewGroupEntity insertEntity(InnovateReviewGroupEntity innovateGroupEntity) {
        return baseMapper.save(innovateGroupEntity);
    }
}
