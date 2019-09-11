package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntTeacherAchievementInfoEntity;
import com.innovate.modules.enterprise.service.EntTeacherAchievementInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 教师所获奖励信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@RestController
@RequestMapping("enterprise/teacher/achievement")
public class EntTeacherAchievementInfoController {
    @Autowired
    private EntTeacherAchievementInfoService entTeacherAchievementInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:teacher:achievement:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entTeacherAchievementInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{teaAchievementId}")
    @RequiresPermissions("enterprise:teacher:achievement:info")
    public R info(@PathVariable("teaAchievementId") Long teaAchievementId){
		EntTeacherAchievementInfoEntity entTeacherAchievementInfo = entTeacherAchievementInfoService.selectById(teaAchievementId);

        return R.ok().put("entTeacherAchievementInfo", entTeacherAchievementInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:teacher:achievement:save")
    public R save(@RequestBody EntTeacherAchievementInfoEntity entTeacherAchievementInfo){
		entTeacherAchievementInfoService.insert(entTeacherAchievementInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:teacher:achievement:update")
    public R update(@RequestBody EntTeacherAchievementInfoEntity entTeacherAchievementInfo){
		entTeacherAchievementInfoService.updateById(entTeacherAchievementInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:teacher:achievement:delete")
    public R delete(@RequestBody Long[] teaAchievementIds){
		entTeacherAchievementInfoService.selectBatchIds(Arrays.asList(teaAchievementIds));

        return R.ok();
    }

}
