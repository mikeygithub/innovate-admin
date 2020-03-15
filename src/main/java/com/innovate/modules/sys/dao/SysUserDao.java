package com.innovate.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	/**
	 * 根据手机号，查询系统用户
	 */
	List<SysUserEntity> queryByUserMobile(String mobile);

	/**
	 * 查询所有系统用户
	 */
	List<SysUserEntity> queryByUser();

	/**
	 * 根据条件查询所有系统用户
	 */
	List<SysUserEntity> queryUser(Map<String, Object> params);

	/**
	 * 根据条件查询所有系统用户,统计总个数
	 */
	int queryCount(Map<String, Object> params);

	/**
	 * 更新用户状态
	 * @param userId 用户ID
	 * @param status 用户状态
	 * @return
	 */
	boolean updateState(Long userId, Integer status);

}
