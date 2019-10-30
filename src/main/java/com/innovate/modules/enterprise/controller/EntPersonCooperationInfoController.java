package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.HasAdminRole;
import com.innovate.modules.enterprise.entity.EntPersonCooperationInfoEntity;
import com.innovate.modules.enterprise.service.EntPersonCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 项目合作人表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@RestController
@RequestMapping("enterprise/person/cooperation")
public class EntPersonCooperationInfoController extends AbstractController {
    @Autowired
    private EntPersonCooperationInfoService entPersonCooperationInfoService;

    @Autowired
    private EntProjectInfoService entProjectInfoService;

    /**
     * 列表
     */
    @HasAdminRole(
            targetType = java.util.Map.class,
            index = 0, roleIds = {"9","10"}, perRoleId = "11",
            perRoleKey = "user_per_id", teacherRoleId = "12",
            teacherRoleKey = "user_teacher_id", entRoleId = "7",entRoleKey = "ent_info_id"
    )
    @RequestMapping("/list")
    // @RequiresPermissions("enterprise:person:cooperation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entPersonCooperationInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @HasAdminRole(
            targetType = java.util.Map.class,
            index = 0, roleIds = {"9","10"}, perRoleId = "11",
            perRoleKey = "user_per_id", teacherRoleId = "12",
            teacherRoleKey = "user_teacher_id", entRoleId = "7",entRoleKey = "ent_info_id"
    )
    @RequestMapping("/proList")
    // @RequiresPermissions("enterprise:project:info:list")
    public R proList(@RequestParam Map<String, Object> params){
        PageUtils page = entPersonCooperationInfoService.queryPageList(params);

        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{proInfoId}/{inType}/{inApply}")
    // @RequiresPermissions("enterprise:person:cooperation:info")
    public R info(@PathVariable("proInfoId") Long proInfoId, @PathVariable("inType") String inType, @PathVariable("inApply") String inApply){
		// EntPersonCooperationInfoEntity entPersonCooperationInfo = entPersonCooperationInfoService.selectById(proCooperationId);
        //return R.ok().put("entPersonCooperationInfo", entPersonCooperationInfo);
        return entProjectInfoService.queryProjectPersonCooperationInfo(proInfoId, inType, inApply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("enterprise:person:cooperation:save")
    public R save(Long proInfoId){
		return entPersonCooperationInfoService.savePersonCooper(proInfoId, getUserId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("enterprise:person:cooperation:update")
    public R update(@RequestParam Map<String,Object> params){
		return entPersonCooperationInfoService.updatePersonCooperation(params);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:person:cooperation:delete")
    public R delete(@RequestBody Long[] proCooperationIds){
		entPersonCooperationInfoService.deleteBatchIds(Arrays.asList(proCooperationIds));

        return R.ok();
    }

}
