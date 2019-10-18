package com.innovate.modules.finish.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.innovate.modules.sys.entity.SysUserEntity;
import lombok.Data;

/**
 * 
 * 
 * @author Mikey
 * @email biaogejiushibiao@outlook.com
 * @date 2019-10-16 22:02:23
 */
@Data
@TableName("innovate_finish_in_expert")
public class FinishInExpertEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer expertId;
	/**
	 * 对应汇总表id
	 */
	private Integer expertCollectId;
	/**
	 * 用户ID
	 */
	private String expertUserId;
	/**
	 * 是否删除
	 */
	private Integer isDel;

	@TableField(exist = false,el = "com.innovate.modules.sys.entity.SysUserEntity")
    private SysUserEntity sysUserEntity;

}
