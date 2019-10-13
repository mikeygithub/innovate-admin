package com.innovate.modules.enterprise.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.enterprise.entity.EntTeacherAttachmentEntity;

import java.util.List;
import java.util.Map;

/**
 * 教师附件表
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-10-10 10:10:39
 */
public interface EntTeacherAttachmentService extends IService<EntTeacherAttachmentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //教师id
    EntTeacherAttachmentEntity findByUserTeacherId(Long userTeacherId);
    //项目信息外键
    EntTeacherAttachmentEntity findByProInfoId(Long proInfoId);
    //项目合作外键
    EntTeacherAttachmentEntity findByProCooperationInfoId(Long proCooperationInfoId);
    //教师工作/项目经历外键
    List<EntTeacherAttachmentEntity> findByTeaExperienceId(Long teaExperienceId);
    //教师所获奖励/证书外键
    EntTeacherAttachmentEntity findByTeaAchievementId(Long teaAchievementId);
    //知识产权外键
    EntTeacherAttachmentEntity findByPatentInfoId(Long patentInfoId);
}

