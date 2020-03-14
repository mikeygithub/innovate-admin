package com.innovate.modules.innovate.controller;

import com.google.gson.Gson;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.ProjectApplyUpdateEntity;
import com.innovate.modules.innovate.service.ProjectApplyUpdateService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目申请修改
 **/
@RestController
@RequestMapping("innovate/project/apply/update")
public class ProjectApplyUpdateController extends AbstractController {

    @Autowired
    private ProjectApplyUpdateService projectApplyUpdateService;


    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:apply:save")
    public R list(@RequestParam Map<String, Object> params) {
        List<ProjectApplyUpdateEntity> page = projectApplyUpdateService.queryAll(params);

        return R.ok().put("page", page);
    }

    /**
     * 申请修改
     */
    @PostMapping("/apply")
    @RequiresPermissions("innovate:apply:save")
    public R applyUpdate(@RequestParam Map<String, Object> params) {
        projectApplyUpdateService.applyUpdate(params);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:apply:save")
    public R update(@RequestBody(required = false) ProjectApplyUpdateEntity projectApplyUpdateEntity) {
        projectApplyUpdateService.update(projectApplyUpdateEntity);
        return R.ok();
    }
}
