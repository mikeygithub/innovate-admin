package com.innovate.modules.check.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 中期检查表
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@Data
@TableName("innovate_check_info")
public class InnovateCheckInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long checkId;
	/**
	 * 大创项目外键
	 */
	private Long declareId;
	/**
	 * 评审组外键
	 */
	private Long groupId;
	/**
	 * 是否是要修改的项目
	 */
	private Integer isUpdate;
	/**
	 * 是否申请修改
	 */
	private Integer applyUpdate;
	/**
	 * 申报不通过
	 */
	private Integer checkNoPass;
	/**
	 * 二级学院
	 */
	private Long instituteId;
	/**
	 * 检查平均分
	 */
	private Double checkScoreAvg;
	/**
	 * 状态标识
	 */
	private Integer projectCheckApplyStatus;
	/**
	 * 时间
	 */
	private Date checkTime;
	/**
	 * 删除标识
	 */
	private Integer isDel;

}
