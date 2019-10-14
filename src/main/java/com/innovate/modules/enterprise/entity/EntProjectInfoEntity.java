package com.innovate.modules.enterprise.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目信息表
 * 
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@Data
@TableName("ent_project_info")
public class EntProjectInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 项目信息主键
	 */
	@TableId
	private Long proInfoId;
	/**
	 * 项目名称
	 */
	private String proName;
	/**
	 * 项目登记
	 */
	private String proRegister;
	/**
	 * 项目来源
	 */
	private String proOrigin;
	/**
	 * 项目经费
	 */
	private String proOutlay;
	/**
	 * 项目类型
	 */
	private Integer proType;
	/**
	 * 项目介绍
	 */
	private String proIntroduce;

	/**
	 *企业信息表外键
	 */
	private Long entInfoId;

	/**
	 *学生信息表外键
	 */
	private Long userPerId;

	/**
	 *教师信息表外键
	 */
	private Long userTeacherId;

	/**
	 *审核状态，0待审核 1已审核
	 */
	private String inApply;

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

	/**
	 * 企业信息
	 */
	@TableField(exist = false)
	private EntEnterpriseInfoEntity entEnterpriseInfo;

}
