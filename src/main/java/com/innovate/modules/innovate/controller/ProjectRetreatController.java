package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.ProjectRetreatEntity;
import com.innovate.modules.innovate.service.ProjectRetreatService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
@RestController
@RequestMapping("innovate/project/retreat")
public class ProjectRetreatController extends AbstractController {

    @Autowired
    private ProjectRetreatService projectRetreatService;

    /**
     * 不通过
     */
    @PostMapping("/retreat")
    @RequiresPermissions("innovate:project:retreat")
    public R retreat(@RequestParam Map<String, Object> params) {
        projectRetreatService.updateRetreat(params);
        return R.ok();
    }

    /**
     * 回退修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:project:retreat")
    public R update(@RequestParam Map<String, Object> params) {
        projectRetreatService.updateRetreat(params);
        return R.ok();
    }

    @PostMapping("/query")
    @RequiresPermissions("innovate:project:list")
    public R noPass(@RequestParam Map<String, Object> params){
        List<ProjectRetreatEntity> retreatEntityList =  projectRetreatService.queryAll(params);
        return R.ok().put("retreatEntityList",retreatEntityList);
    }
}
