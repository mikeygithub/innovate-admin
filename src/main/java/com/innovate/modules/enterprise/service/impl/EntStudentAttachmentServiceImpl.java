package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.enterprise.dao.EntStudentAttachmentDao;
import com.innovate.modules.enterprise.entity.EntStudentAttachmentEntity;
import com.innovate.modules.enterprise.service.EntStudentAttachmentService;


@Service("entStudentAttachmentService")
public class EntStudentAttachmentServiceImpl extends ServiceImpl<EntStudentAttachmentDao, EntStudentAttachmentEntity> implements EntStudentAttachmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntStudentAttachmentEntity> page = this.selectPage(
                new Query<EntStudentAttachmentEntity>(params).getPage(),
                new EntityWrapper<EntStudentAttachmentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public EntStudentAttachmentEntity findByUserPerId(Long userPerId) {
        return baseMapper.findByUserPerId(userPerId);
    }

    @Override
    public EntStudentAttachmentEntity findByProInfoId(Long proInfoId) {
        return baseMapper.findByProInfoId(proInfoId);
    }

    @Override
    public EntStudentAttachmentEntity findByProCooperationInfoId(Long proCooperationInfoId) {
        return baseMapper.findByProCooperationInfoId(proCooperationInfoId);
    }

    @Override
    public List<EntStudentAttachmentEntity> findByStuExperienceId(Long stuExperienceId) {

        EntityWrapper<EntStudentAttachmentEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("stu_experience_id", stuExperienceId);
        return this.selectList(wrapper);

//        return baseMapper.findByStuExperienceId(stuExperienceId);
    }

    @Override
    public EntStudentAttachmentEntity findByStuAchievementId(Long stuAchievementId) {
        return baseMapper.findByStuAchievementId(stuAchievementId);
    }

    @Override
    public void deleteBatchStuExperienceIds(Long[] stuExperienceIds) {

        EntityWrapper<EntStudentAttachmentEntity> wrapper = new EntityWrapper<>();
        wrapper.in("stu_experience_id", stuExperienceIds);
        this.delete(wrapper);
    }

    @Override
    public void deleteBatchStuAchievementIds(Long[] stuAchievementIds) {

        EntityWrapper<EntStudentAttachmentEntity> wrapper = new EntityWrapper<>();
        wrapper.in("stu_achievement_id", stuAchievementIds);
        this.delete(wrapper);
    }

}