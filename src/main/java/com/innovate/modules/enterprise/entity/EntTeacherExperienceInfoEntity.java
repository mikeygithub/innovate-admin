package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 教师科研经历/成果信息表
 * 
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@Data
@TableName("ent_teacher_experience_info")
public class EntTeacherExperienceInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long teaExperienceId;
	/**
	 * 教师基本信息表外键
	 */
	private Long teaInfoId;
	/**
	 * 科研内容
	 */
	private String teaExperienceContent;

}
