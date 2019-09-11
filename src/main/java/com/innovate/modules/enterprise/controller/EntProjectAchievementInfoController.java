package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntProjectAchievementInfoEntity;
import com.innovate.modules.enterprise.service.EntProjectAchievementInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 项目成果信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@RestController
@RequestMapping("enterprise/project/achievement")
public class EntProjectAchievementInfoController {
    @Autowired
    private EntProjectAchievementInfoService entProjectAchievementInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:project:achievement:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entProjectAchievementInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{proAchievementId}")
    @RequiresPermissions("enterprise:project:achievement:info")
    public R info(@PathVariable("proAchievementId") Long proAchievementId){
		EntProjectAchievementInfoEntity entProjectAchievementInfo = entProjectAchievementInfoService.selectById(proAchievementId);

        return R.ok().put("entProjectAchievementInfo", entProjectAchievementInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:project:achievement:save")
    public R save(@RequestBody EntProjectAchievementInfoEntity entProjectAchievementInfo){
		entProjectAchievementInfoService.insert(entProjectAchievementInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:project:achievement:update")
    public R update(@RequestBody EntProjectAchievementInfoEntity entProjectAchievementInfo){
		entProjectAchievementInfoService.updateById(entProjectAchievementInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:project:achievement:delete")
    public R delete(@RequestBody Long[] proAchievementIds){
		entProjectAchievementInfoService.deleteBatchIds(Arrays.asList(proAchievementIds));

        return R.ok();
    }

}
