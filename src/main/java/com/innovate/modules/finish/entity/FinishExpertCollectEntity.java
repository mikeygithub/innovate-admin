package com.innovate.modules.finish.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("innovate_finish_expert_collect")
public class FinishExpertCollectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer expertCollectId;
	/**
	 * 年度
	 */
	private Date expertCollectTime;
	/**
	 * 学院
	 */
	private String expertCollectInstituteId;
	/**
	 * 邮箱
	 */
	private String expertCollectInstituteEmail;
	/**
	 * 填表人
	 */
	private String expertCollectWriter;
	/**
	 * 填表人联系电话
	 */
	private String expertCollectWriterPhone;

	/**
	 * 提交
	 */
	private Integer expertApply;
	/**
	 * 是否删除
	 */
	private Integer isDel;
	/**
	 * 外部专家
	 */
	@TableField(exist = false,el = "com.innovate.modules.finish.entity.FinishOutExpertEntity")
	private List<FinishOutExpertEntity> finishOutExpertEntities;
	/**
	 * 内部教师
	 */
	@TableField(exist = false,el = "com.innovate.modules.finish.entity.FinishInExpertEntity")
	private List<FinishInExpertEntity> finishInExpertEntities;

}
