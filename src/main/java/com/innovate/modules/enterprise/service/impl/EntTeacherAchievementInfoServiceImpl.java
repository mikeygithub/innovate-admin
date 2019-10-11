package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.enterprise.entity.EntTeacherAttachmentEntity;
import com.innovate.modules.enterprise.service.EntTeacherAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.enterprise.dao.EntTeacherAchievementInfoDao;
import com.innovate.modules.enterprise.entity.EntTeacherAchievementInfoEntity;
import com.innovate.modules.enterprise.service.EntTeacherAchievementInfoService;


@Service("entTeacherAchievementInfoService")
public class EntTeacherAchievementInfoServiceImpl extends ServiceImpl<EntTeacherAchievementInfoDao, EntTeacherAchievementInfoEntity> implements EntTeacherAchievementInfoService {

    @Autowired
    private EntTeacherAttachmentService entTeacherAttachmentService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Object userId = params.get("userId");
        Object hasApply = params.get("hasApply");

        EntityWrapper ew = new EntityWrapper<>();
        ew.setEntity(new EntTeacherAchievementInfoEntity());
        //教师查询
        if (userId!=null&&userId!="") ew.eq("user_teacher_id",Long.parseLong(userId.toString()));
        //1:未审批 2:已通过 3:未通过
        if (hasApply!=null)ew.eq("in_apply",Long.parseLong(hasApply.toString()));

        Page<EntTeacherAchievementInfoEntity> page = this.selectPage(
                new Query<EntTeacherAchievementInfoEntity>(params).getPage(), ew
        );
        return new PageUtils(page);
    }

    @Override
    public void save(EntTeacherAchievementInfoEntity entTeacherAchievementInfoEntity) {
        insert(entTeacherAchievementInfoEntity);
        EntTeacherAttachmentEntity atta = entTeacherAchievementInfoEntity.getEntTeacherAttachmentEntity();
        atta.setTeaAchievementId(entTeacherAchievementInfoEntity.getTeaAchievementId());
        entTeacherAttachmentService.insert(atta);
    }

}