package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntStudentAchievementInfoEntity;
import com.innovate.modules.enterprise.service.EntStudentAchievementInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 学生所获奖励/证书信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@RestController
@RequestMapping("enterprise/student/achievement")
public class EntStudentAchievementInfoController {
    @Autowired
    private EntStudentAchievementInfoService entStudentAchievementInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:student:achievement:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entStudentAchievementInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{stuAchievementId}")
    @RequiresPermissions("enterprise:student:achievement:info")
    public R info(@PathVariable("stuAchievementId") Long stuAchievementId){
		EntStudentAchievementInfoEntity entStudentAchievementInfo = entStudentAchievementInfoService.selectById(stuAchievementId);

        return R.ok().put("entStudentAchievementInfo", entStudentAchievementInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:student:achievement:save")
    public R save(@RequestBody EntStudentAchievementInfoEntity entStudentAchievementInfo){
		entStudentAchievementInfoService.insert(entStudentAchievementInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:student:achievement:update")
    public R update(@RequestBody EntStudentAchievementInfoEntity entStudentAchievementInfo){
		entStudentAchievementInfoService.updateById(entStudentAchievementInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:student:achievement:delete")
    public R delete(@RequestBody Long[] stuAchievementIds){
		entStudentAchievementInfoService.deleteBatchIds(Arrays.asList(stuAchievementIds));

        return R.ok();
    }

}
