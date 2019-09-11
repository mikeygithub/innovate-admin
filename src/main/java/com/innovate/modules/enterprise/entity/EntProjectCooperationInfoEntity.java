package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
	private Long stuInfoId;
	/**
	 * 教师基本信息表外键：合作老师
	 */
	private Long teaInfoId;
	/**
	 * 项目信息外键：合作企业
	 */
	private Long entInfoId;

}
