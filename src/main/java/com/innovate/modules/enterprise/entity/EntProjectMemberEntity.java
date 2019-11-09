package com.innovate.modules.enterprise.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 项目合作成员
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-11-09 14:05:55
 */
@Data
@TableName("ent_project_member")
public class EntProjectMemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long proMemberId;
	/**
	 * 项目信息表主键
	 */
	private Long proInfoId;
	/**
	 * 项目合作成员：学生
	 */
	private Long userPerId;
	/**
	 * 项目合作成员：教师
	 */
	private Long userTeacherId;
	/**
	 * 项目合作成员：企业
	 */
	private Long entInfoId;
	/**
	 * 审核状态，0待审核 1已审核
	 */
	private String inApply;

}
