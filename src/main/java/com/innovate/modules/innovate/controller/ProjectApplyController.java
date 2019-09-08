package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.innovate.service.ProjectApplyService;
import com.innovate.modules.sys.controller.AbstractController;
import com.innovate.modules.sys.entity.SysRoleEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title: 审核
 * @Description:
 * @date 2018/11/8 21:10
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/project/apply")
public class ProjectApplyController extends AbstractController {
    @Autowired
    private ProjectApplyService projectApplyService;

    /**
     * 审核（状态修改）
     * @param
     * @return
     */
    @PostMapping("/apply")
    @RequiresPermissions("innovate:project:info")
    public R apply(@RequestParam Map<String, Object> params){
        projectApplyService.apply(params);
        return R.ok();
    }

}
