package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;
import com.innovate.modules.enterprise.service.EntProjectCooperationInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
public class EntProjectCooperationInfoController {
    @Autowired
    private EntProjectCooperationInfoService entProjectCooperationInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:project:cooperation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entProjectCooperationInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{proCooperationInfoId}")
    @RequiresPermissions("enterprise:project:cooperation:info")
    public R info(@PathVariable("proCooperationInfoId") Long proCooperationInfoId){
		EntProjectCooperationInfoEntity entProjectCooperationInfo = entProjectCooperationInfoService.selectById(proCooperationInfoId);

        return R.ok().put("entProjectCooperationInfo", entProjectCooperationInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:project:cooperation:save")
    public R save(@RequestBody EntProjectCooperationInfoEntity entProjectCooperationInfo){
		entProjectCooperationInfoService.insert(entProjectCooperationInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:project:cooperation:update")
    public R update(@RequestBody EntProjectCooperationInfoEntity entProjectCooperationInfo){
		entProjectCooperationInfoService.updateById(entProjectCooperationInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:project:cooperation:delete")
    public R delete(@RequestBody Long[] proCooperationInfoIds){
		entProjectCooperationInfoService.deleteBatchIds(Arrays.asList(proCooperationInfoIds));

        return R.ok();
    }

}
