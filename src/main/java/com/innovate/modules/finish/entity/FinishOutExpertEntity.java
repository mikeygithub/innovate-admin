package com.innovate.modules.finish.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 
 * 
 * @author Mikey
 * @email biaogejiushibiao@outlook.com
 * @date 2019-10-16 22:02:23
 */
@Data
@TableName("innovate_finish_out_expert")
public class FinishOutExpertEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer expertId;
	/**
	 * 专家姓名
	 */
	private String expertName;
	/**
	 * 职称
	 */
	private String expertTitles;
	/**
	 * 单位
	 */
	private String expertUnit;
	/**
	 * 联系电话
	 */
	private String expertPhone;
	/**
	 * 身份证
	 */
	private String expertIdentity;
	/**
	 * 对应汇总表id
	 */
	private Integer expertCollectId;
	/**
	 * 是否删除
	 */
	private Integer isDel;

}
