package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.HasAdminRole;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;
import com.innovate.modules.enterprise.service.EntProjectCooperationInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 项目合作信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@RestController
@RequestMapping("enterprise/project/cooperation")
public class EntProjectCooperationInfoController extends AbstractController {
    @Autowired
    private EntProjectCooperationInfoService entProjectCooperationInfoService;

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
    //@RequiresPermissions("enterprise:project:cooperation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entProjectCooperationInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 处理项目合作信息审核
     * @return
     */
    @RequestMapping("/entExamine")
    public R entExamine(@RequestParam  Map<String, Object> params){
        logger.info("接收数据:{}", params);
        return entProjectCooperationInfoService.updateProjectExamine(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{proCooperationInfoId}/{inType}")
    //@RequiresPermissions("enterprise:project:cooperation:info")
    public R info(@PathVariable("proCooperationInfoId") Long proCooperationInfoId, @PathVariable("inType") String inType){
		//EntProjectCooperationInfoEntity entProjectCooperationInfo = entProjectCooperationInfoService.selectById(proCooperationInfoId);
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("inType", inType);
        params.put("proCooperationInfoId",proCooperationInfoId);
        return entProjectCooperationInfoService.queryProjectCooperationInfo(params);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("enterprise:project:cooperation:save")
    public R save(@RequestBody EntProjectCooperationInfoEntity entProjectCooperationInfo){
		entProjectCooperationInfoService.insertProjectCooperation(entProjectCooperationInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("enterprise:project:cooperation:update")
    public R update(@RequestBody EntProjectCooperationInfoEntity entProjectCooperationInfo){
		entProjectCooperationInfoService.updateById(entProjectCooperationInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("enterprise:project:cooperation:delete")
    public R delete(@RequestBody Long[] proCooperationInfoIds){
		entProjectCooperationInfoService.deleteBatchIds(Arrays.asList(proCooperationInfoIds));

        return R.ok();
    }

}
