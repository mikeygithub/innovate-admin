package com.innovate.modules.enterprise.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 招聘信息表
 * 
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@Data
@TableName("ent_recruitment_info")
public class EntRecruitmentInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long recruitmentInfoId;
	/**
	 * 项目信息外键
	 */
	private Long proInfoId;
	/**
	 * 工作内容
	 */
	private String jobContent;
	/**
	 * 招聘岗位
	 */
	private String recruitmentPost;
	/**
	 * 岗位要求
	 */
	private String postRequire;
	/**
	 * 招聘人数
	 */
	private Integer recruitmentPeopleNumber;
	/**
	 * 招聘专业
	 */
	private Integer recruitmentSpecialty;
	/**
	 * 工作方式
	 */
	private String workWay;

}
