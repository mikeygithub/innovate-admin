package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 项目信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@RestController
@RequestMapping("enterprise/project/info")
public class EntProjectInfoController {
    @Autowired
    private EntProjectInfoService entProjectInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:project:info:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entProjectInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{proInfoId}")
    @RequiresPermissions("enterprise:project:info")
    public R info(@PathVariable("proInfoId") Long proInfoId){
		EntProjectInfoEntity entProjectInfo = entProjectInfoService.selectById(proInfoId);

        return R.ok().put("entProjectInfo", entProjectInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:project:info:save")
    public R save(@RequestBody EntProjectInfoEntity entProjectInfo){
		entProjectInfoService.insert(entProjectInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:project:info:update")
    public R update(@RequestBody EntProjectInfoEntity entProjectInfo){
		entProjectInfoService.updateById(entProjectInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:project:info:delete")
    public R delete(@RequestBody Long[] proInfoIds){
		entProjectInfoService.deleteBatchIds(Arrays.asList(proInfoIds));

        return R.ok();
    }

}
