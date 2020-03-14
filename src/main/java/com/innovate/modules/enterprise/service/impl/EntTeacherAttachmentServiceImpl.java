package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.enterprise.dao.EntTeacherAttachmentDao;
import com.innovate.modules.enterprise.entity.EntTeacherAttachmentEntity;
import com.innovate.modules.enterprise.service.EntTeacherAttachmentService;


@Service("entTeacherAttachmentService")
public class EntTeacherAttachmentServiceImpl extends ServiceImpl<EntTeacherAttachmentDao, EntTeacherAttachmentEntity> implements EntTeacherAttachmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Page<EntTeacherAttachmentEntity> page = this.selectPage(
                new Query<EntTeacherAttachmentEntity>(params).getPage(),
                new EntityWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public EntTeacherAttachmentEntity findByUserTeacherId(Long userTeacherId) {
        return baseMapper.findByUserTeacherId(userTeacherId);
    }

    @Override
    public EntTeacherAttachmentEntity findByProInfoId(Long proInfoId) {
        return baseMapper.findByProInfoId(proInfoId);
    }

    @Override
    public EntTeacherAttachmentEntity findByProCooperationInfoId(Long proCooperationInfoId) {
        return baseMapper.findByProCooperationInfoId(proCooperationInfoId);
    }

    @Override
    public List<EntTeacherAttachmentEntity> findByTeaExperienceId(Long teaExperienceId) {
        return baseMapper.findByTeaExperienceId(teaExperienceId);
    }

    @Override
    public EntTeacherAttachmentEntity findByTeaAchievementId(Long teaAchievementId) {
        return baseMapper.findByTeaAchievementId(teaAchievementId);
    }

    @Override
    public EntTeacherAttachmentEntity findByPatentInfoId(Long patentInfoId) {
        return baseMapper.findByProInfoId(patentInfoId);
    }

}