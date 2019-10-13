package com.innovate.modules.enterprise.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.enterprise.entity.EntTeacherAttachmentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 教师附件表
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-10-10 10:10:39
 */
@Mapper
public interface EntTeacherAttachmentDao extends BaseMapper<EntTeacherAttachmentEntity> {
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
