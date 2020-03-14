package com.innovate.modules.check.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 中期检查回退
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@Data
@TableName("innovate_check_retreat")
public class InnovateCheckRetreatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 回退id
	 */
	@TableId
	private Long retreatId;
	/**
	 * 中期检查id
	 */
	private Long checkId;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 中期检查流程
	 */
	private String apply;
	/**
	 * 流程进度值
	 */
	private Integer applyStatus;
	/**
	 * 回退建议
	 */
	private String retreatOption;
	/**
	 * 是否修改
	 */
	private Integer hasUpdate;
	/**
	 * 
	 */
	private Integer isDel;

}
