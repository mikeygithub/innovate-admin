package com.innovate.modules.sys.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:13
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 菜单ID
	 */
	private Long menuId;
	
}
