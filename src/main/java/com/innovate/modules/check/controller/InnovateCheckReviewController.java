package com.innovate.modules.check.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.check.entity.InnovateCheckAwardEntity;
import com.innovate.modules.check.entity.InnovateCheckReviewEntity;
import com.innovate.modules.check.service.InnovateCheckAwardService;
import com.innovate.modules.check.service.InnovateCheckReviewService;
import com.innovate.modules.declare.entity.DeclareReviewEntity;
import com.innovate.modules.declare.service.DeclareReviewService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 21:10
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/check/review")
public class InnovateCheckReviewController extends AbstractController {
    @Autowired
    private InnovateCheckReviewService innovateCheckReviewService;
    /**
     * 分配评委组绑定用户
     */
    @PostMapping("/review")
    @RequiresPermissions("innovate:check:list")
    public R review(@RequestBody Map<String,Object> params){
        innovateCheckReviewService.reviewUser(params);
        return R.ok();
    }

    /**
     * 评分
     */
    @PostMapping("/score")
    @RequiresPermissions("innovate:check:list")
    public R score(@RequestBody(required = false) InnovateCheckReviewEntity innovateCheckReviewEntity){
        innovateCheckReviewService.score(innovateCheckReviewEntity);
        return R.ok();
    }

    /**
     * 查询评分
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:check:list")
    public R info(@RequestParam Map<String,Object> params){
        InnovateCheckReviewEntity innovateCheckReviewEntity = innovateCheckReviewService.queryScore(params);
        return R.ok().put("checkReviewEntity",innovateCheckReviewEntity);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:check:list")
    public R update(@RequestBody InnovateCheckReviewEntity innovateCheckReviewEntity){
        innovateCheckReviewService.updateAllColumnById(innovateCheckReviewEntity);
        return R.ok();
    }

}
