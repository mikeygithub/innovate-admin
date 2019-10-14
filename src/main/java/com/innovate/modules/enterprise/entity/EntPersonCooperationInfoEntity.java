package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目合作人表
 * 
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@Data
@TableName("ent_person_cooperation_info")
public class EntPersonCooperationInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long proCooperationId;
	/**
	 * 主键
	 */
	private Long proCooperationInfoId;
	/**
	 * 学生基本信息表外键：合作学生
	 */
	private Long userPerId;
	/**
	 * 教师基本信息表外键：合作老师
	 */
	private Long userTeacherId;
	/**
	 * 项目信息外键：合作企业
	 */
	private Long entInfoId;

	/**
	 * 审核状态，0待审核 1已审核
	 */
	private String inApply;

}
