package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 教师附件表
 * 
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
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
	private Long teaInfoId;
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

}
