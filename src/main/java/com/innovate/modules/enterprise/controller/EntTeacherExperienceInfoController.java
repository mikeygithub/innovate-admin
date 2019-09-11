package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntTeacherExperienceInfoEntity;
import com.innovate.modules.enterprise.service.EntTeacherExperienceInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 教师科研经历/成果信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@RestController
@RequestMapping("enterprise/teacher/experience")
public class EntTeacherExperienceInfoController {
    @Autowired
    private EntTeacherExperienceInfoService entTeacherExperienceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:teacher:experience:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entTeacherExperienceInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{teaExperienceId}")
    @RequiresPermissions("enterprise:teacher:experience:info")
    public R info(@PathVariable("teaExperienceId") Long teaExperienceId){
		EntTeacherExperienceInfoEntity entTeacherExperienceInfo = entTeacherExperienceInfoService.selectById(teaExperienceId);

        return R.ok().put("entTeacherExperienceInfo", entTeacherExperienceInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:teacher:experience:save")
    public R save(@RequestBody EntTeacherExperienceInfoEntity entTeacherExperienceInfo){
		entTeacherExperienceInfoService.insert(entTeacherExperienceInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:teacher:experience:update")
    public R update(@RequestBody EntTeacherExperienceInfoEntity entTeacherExperienceInfo){
		entTeacherExperienceInfoService.updateById(entTeacherExperienceInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:teacher:experience:delete")
    public R delete(@RequestBody Long[] teaExperienceIds){
		entTeacherExperienceInfoService.deleteBatchIds(Arrays.asList(teaExperienceIds));

        return R.ok();
    }

}
