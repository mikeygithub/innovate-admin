package com.innovate.modules.enterprise.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 教师附件表
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-10-10 10:10:39
 */
@Data
@TableName("ent_teacher_attachment")
public class EntTeacherAttachmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long teaAttachmentId;
	/**
	 * 教师基本信息表外键
	 */
	private Long userTeacherId;
	/**
	 * 项目信息外键
	 */
	private Long proInfoId;
	/**
	 * 项目合作外键
	 */
	private Long proCooperationInfoId;
	/**
	 * 教师工作/项目经历外键
	 */
	private Long teaExperienceId;
	/**
	 * 教师所获奖励/证书外键
	 */
	private Long teaAchievementId;
	/**
	 * 知识产权外键
	 */
	private Long patentInfoId;
	/**
	 * 文件路径
	 */
	private String teaAttachmentUrl;
	//名称
	private String teaAttachmentName;
	/**
	 * 审核状态，0待审核 1已审核
	 */
	private Integer inApply;

}
