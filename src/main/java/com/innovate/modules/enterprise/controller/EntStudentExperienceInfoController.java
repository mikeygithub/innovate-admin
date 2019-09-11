package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntStudentExperienceInfoEntity;
import com.innovate.modules.enterprise.service.EntStudentExperienceInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 学生工作/项目经历信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@RestController
@RequestMapping("enterprise/student/experience")
public class EntStudentExperienceInfoController {
    @Autowired
    private EntStudentExperienceInfoService entStudentExperienceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:student:experience:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entStudentExperienceInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{stuExperienceId}")
    @RequiresPermissions("enterprise:student:experience:info")
    public R info(@PathVariable("stuExperienceId") Long stuExperienceId){
		EntStudentExperienceInfoEntity entStudentExperienceInfo = entStudentExperienceInfoService.selectById(stuExperienceId);

        return R.ok().put("entStudentExperienceInfo", entStudentExperienceInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:student:experience:save")
    public R save(@RequestBody EntStudentExperienceInfoEntity entStudentExperienceInfo){
		entStudentExperienceInfoService.insert(entStudentExperienceInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:student:experience:update")
    public R update(@RequestBody EntStudentExperienceInfoEntity entStudentExperienceInfo){
		entStudentExperienceInfoService.updateById(entStudentExperienceInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:student:experience:delete")
    public R delete(@RequestBody Long[] stuExperienceIds){
		entStudentExperienceInfoService.deleteBatchIds(Arrays.asList(stuExperienceIds));

        return R.ok();
    }

}
