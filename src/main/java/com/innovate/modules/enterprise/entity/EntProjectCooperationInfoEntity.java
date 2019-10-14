package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目合作信息表
 * 
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@Data
@TableName("ent_project_cooperation_info")
public class EntProjectCooperationInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long proCooperationInfoId;
	/**
	 * 项目信息外键
	 */
	private Long proInfoId;
	/**
	 * 合作内容
	 */
	private String cooperationContent;
	/**
	 * 合作方式
	 */
	private String cooperationType;
	/**
	 * 合作要求
	 */
	private String cooperationRequire;
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

	/**
	 * 项目信息实体
	 */
	@TableField(exist = false)
	private EntProjectInfoEntity projectInfo;

	/**
	 * 企业信息
	 */
	@TableField(exist = false)
	private EntEnterpriseInfoEntity entEnterpriseInfo;

	/**
	 * 学生用户信息
	 */
	@TableField(exist = false)
	private UserPersonInfoEntity userPersonInfo;

	/**
	 * 教师用户信息
	 */
	@TableField(exist = false)
	private UserTeacherInfoEntity userTeacherInfo;

}
