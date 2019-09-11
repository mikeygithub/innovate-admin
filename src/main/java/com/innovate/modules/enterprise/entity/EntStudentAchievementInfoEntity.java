package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生所获奖励/证书信息表
 * 
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@Data
@TableName("ent_student_achievement_info")
public class EntStudentAchievementInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long stuAchievementId;
	/**
	 * 学生基本信息表外键
	 */
	private Long stuInfoId;
	/**
	 * 所获奖励/证书内容
	 */
	private String stuAchievementContent;
	/**
	 * 奖励/证书获得时间
	 */
	private Date achievementTime;
	/**
	 * 奖励/证书获得地点
	 */
	private String achievementPlace;
	/**
	 * 奖励/证书级别
	 */
	private String achievementAgree;
	/**
	 * 奖励类型:1学科竞赛，2专业比赛 ；   证书:1资格证，2等级证
	 */
	private Integer achievementType;

}
