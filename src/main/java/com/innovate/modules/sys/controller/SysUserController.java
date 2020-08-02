package com.innovate.modules.sys.controller;

import com.innovate.common.annotation.SysLog;
import com.innovate.common.utils.*;
import com.innovate.common.validator.Assert;
import com.innovate.common.validator.ValidatorUtils;
import com.innovate.common.validator.group.AddGroup;
import com.innovate.common.validator.group.UpdateGroup;
import com.innovate.modules.innovate.utils.SmsUtil;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.entity.SysUserEntityModel;
import com.innovate.modules.sys.entity.SysUserModel;
import com.innovate.modules.sys.form.PasswordForm;
import com.innovate.modules.sys.service.SysUserRoleService;
import com.innovate.modules.sys.service.SysUserService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;


	/**
	 * 所有用户列表
	 */
	@SysLog("sys.user.list")
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			params.put("createUserId", getUserId());
//		}
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 二级学院所有用户列表
	 */
	@GetMapping("/erlist")
	public R erlist(@RequestParam Map<String, Object> params){
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}


	/**
	 * 所有用户列表
	 */
	@GetMapping("/all")
	@RequiresPermissions("sys:user:list")
	public R all(){
		List<SysUserEntity> sysUserEntities = sysUserService.queryByUser();

		return R.ok().put("sysUserEntities", sysUserEntities);
	}

	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		SysUserEntity user = getUser();
		List<Long> userRoles = sysUserRoleService.queryRoleIdList(user.getUserId());
		user.setRoleIdList(userRoles);
		return R.ok().put("user", user);
	}

	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");

		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}

		return R.ok();
	}

	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.selectById(userId);

		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		return R.ok().put("user", user);
	}

	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		//校验对象
		ValidatorUtils.validateEntity(user, AddGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.save(user);

		return R.ok();
	}

	/**
	 * 用于学院账号批量导入学院用户-项目负责人或学生
	 */
	@SysLog("批量保存用户")
	@PostMapping("/batchsave")
	@RequiresPermissions("sys:eruser:batchsave")
	public R batchSave(@RequestBody(required = false) SysUserModel sysUserModel){

		List<SysUserEntityModel> errorDate = new ArrayList<>();
		SysUserEntity user;
		Long roleIds[];
		for (SysUserEntityModel sysUserEntityModel : sysUserModel.getBatchSaveList()) {
			// 校验手机号、邮箱和用户类型
			if (	(!RegularCheckUtils.isPhone(sysUserEntityModel.get__EMPTY_2())) ||
					(!RegularCheckUtils.isEmail(sysUserEntityModel.get__EMPTY_3())) ||
					(!RoleUtils.checkRole(sysUserEntityModel.get__EMPTY_4()))) {
				errorDate.add(sysUserEntityModel);
				continue;
			}
			user = new SysUserEntity();
			user.setUsername(sysUserEntityModel.get__EMPTY());
			user.setName(sysUserEntityModel.get__EMPTY_1());
			user.setPassword("123456");
			user.setEmail(sysUserEntityModel.get__EMPTY_3());
			user.setMobile(sysUserEntityModel.get__EMPTY_2());
			user.setStatus(1);
			user.setInstituteId(sysUserModel.getErInstituteId());
			user.setCreateUserId((long) 1);
			//转换Long类型的数组
			roleIds = (Long[]) ConvertUtils.convert(sysUserEntityModel.get__EMPTY_4().split(","), Long.class);
			user.setRoleIdList(Arrays.asList(roleIds));
			try {
				sysUserService.save(user);
			} catch (Exception e) {
				e.printStackTrace();
				return R.error().put("errorDate", sysUserModel.getBatchSaveList());
			}
		}

		return errorDate.isEmpty() ? R.ok() : R.error().put("errorDate", errorDate);
	}

	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);

		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}

		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}

		sysUserService.deleteBatch(userIds);

		return R.ok();
	}


}
