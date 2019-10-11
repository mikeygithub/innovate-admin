package com.innovate.modules.enterprise.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 教师所获奖励信息表
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-10-10 10:10:39
 */
@Data
@TableName("ent_teacher_achievement_info")
public class EntTeacherAchievementInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long teaAchievementId;
	// 教师基本信息表外键
	private Long userTeacherId;
	// 教师所获奖励内容
	private String teaAchievementContent;
	//审核状态，0待审核 1已审核
	private Integer inApply;
	//附件
	@TableField(exist = false,el = "com.innovate.modules.enterprise.entity.EntEnterpriseAttachmentEntity")
	private EntTeacherAttachmentEntity entTeacherAttachmentEntity;
}
