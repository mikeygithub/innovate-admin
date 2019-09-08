package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.*;
import com.innovate.modules.innovate.service.*;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 21:10
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/project/review")
public class ProjectReviewController extends AbstractController {
    @Autowired
    private ProjectReviewService projectReviewService;
    /**
     * 分配评委组绑定用户
     */
    @PostMapping("/review")
    @RequiresPermissions("innovate:project:list")
    public R review(@RequestBody Map<String,Object> params){
        projectReviewService.reviewUser(params);
        return R.ok();
    }

    /**
     * 评分
     */
    @PostMapping("/score")
    @RequiresPermissions("innovate:project:list")
    public R score(@RequestBody(required = false) ProjectReviewEntity projectReviewEntity){
        projectReviewService.score(projectReviewEntity);
        return R.ok();
    }

    /**
     * 查询评分
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:project:list")
    public R info(@RequestParam Map<String,Object> params){
        ProjectReviewEntity projectReviewEntity = projectReviewService.queryScore(params);
        return R.ok().put("projectReviewEntity",projectReviewEntity);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:project:list")
    public R update(@RequestBody ProjectReviewEntity projectReviewEntity){
        projectReviewService.updateAllColumnById(projectReviewEntity);
        return R.ok();
    }

}
