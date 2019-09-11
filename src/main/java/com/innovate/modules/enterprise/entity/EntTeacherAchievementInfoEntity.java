package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 教师所获奖励信息表
 * 
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@Data
@TableName("ent_teacher_achievement_info")
public class EntTeacherAchievementInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long teaAchievementId;
	/**
	 * 教师基本信息表外键
	 */
	private Long teaInfoId;
	/**
	 * 教师所获奖励内容
	 */
	private String teaAchievementContent;

}
