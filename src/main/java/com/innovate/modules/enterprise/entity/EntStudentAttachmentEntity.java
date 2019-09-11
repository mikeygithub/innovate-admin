package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 学生附件表
 * 
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@Data
@TableName("ent_student_attachment")
public class EntStudentAttachmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long stuAttachmentId;
	/**
	 * 学生基本信息表外键
	 */
	private Long stuInfoId;
	/**
	 * 项目信息外键
	 */
	private Long proInfoId;
	/**
	 * 项目合作外键
	 */
	private Long proCooperationInfoId;
	/**
	 * 学生工作/项目经历外键
	 */
	private Long stuExperienceId;
	/**
	 * 学生所获奖励/证书外键
	 */
	private Long stuAchievementId;
	/**
	 * 文件路径
	 */
	private String stuAttachmentUrl;

}
