package com.innovate.modules.check.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 中期检查附件表
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@Data
@TableName("innovate_check_attach")
public class InnovateCheckAttachEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 附件id
	 */
	@TableId
	private Long attachId;
	/**
	 * 中期检查外键
	 */
	private Long checkId;
	/**
	 * 附件名称
	 */
	private String attachName;
	/**
	 * 附件路径
	 */
	private String attachPath;
	/**
	 * 
	 */
	private Integer isDel;

}
