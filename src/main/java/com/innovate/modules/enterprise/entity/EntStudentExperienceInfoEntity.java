package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生工作/项目经历信息表
 * 
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@Data
@TableName("ent_student_experience_info")
public class EntStudentExperienceInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long stuExperienceId;
	/**
	 * 学生基本信息表外键
	 */
	private Long stuInfoId;
	/**
	 * 工作/项目经历内容
	 */
	private String stuExperienceContent;
	/**
	 * 工作/项目经历时间
	 */
	private Date stuExperienceTime;
	/**
	 * 工作/项目经历地点
	 */
	private String stuExperiencePlace;
	/**
	 * 经历工作/项目类型
	 */
	private String stuExperienceWorkType;

}
