package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.BaseTeacherInfoEntity;
import com.innovate.modules.innovate.service.BaseTeacherInfoService;
import com.innovate.modules.innovate.utils.BaseCalculateUtil;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:教师
 **/
@RestController
@RequestMapping("innovate/base/teacher")
public class BaseTeacherInfoController extends AbstractController {

    @Autowired
    private BaseTeacherInfoService teacherInfoService;

    private BaseTeacherInfoEntity tempBaseTeacherInfoEntity;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:teacher:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{teacherId}")
    @RequiresPermissions("innovate:teacher:info")
    public R info(@PathVariable("teacherId") Integer teacherId){
        BaseTeacherInfoEntity teacherInfo = teacherInfoService.selectById(teacherId);

        return R.ok().put("teacherInfo", teacherInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:teacher:save")
    public R save(@RequestBody BaseTeacherInfoEntity teacherInfoEntity){
        teacherInfoService.insert(teacherInfoEntity);
        BaseCalculateUtil.setCalculateId(teacherInfoEntity.getBaseId());
        BaseCalculateUtil.calculate();
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:teacher:update")
    public R update(@RequestBody BaseTeacherInfoEntity teacherInfoEntity){
        tempBaseTeacherInfoEntity = teacherInfoService.selectById(teacherInfoEntity.getTeacherId());
        teacherInfoService.updateAllColumnById(teacherInfoEntity);
        BaseCalculateUtil.setCalculateId(teacherInfoEntity.getBaseId());
        BaseCalculateUtil.calculate();
        BaseCalculateUtil.setCalculateId(tempBaseTeacherInfoEntity.getBaseId());
        BaseCalculateUtil.calculate();
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:teacher:delete")
    public R delete(@RequestBody Long[] teacherIds){
        for (Long teacherId:teacherIds){
            tempBaseTeacherInfoEntity = teacherInfoService.selectById(teacherId);
            teacherInfoService.deleteById(teacherId);
            BaseCalculateUtil.setCalculateId(tempBaseTeacherInfoEntity.getBaseId());
            BaseCalculateUtil.calculate();
        }
       // teacherInfoService.deleteBatchIds(Arrays.asList(teacherIds));

        return R.ok();
    }
}
