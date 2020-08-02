package com.innovate.modules.declare.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.declare.entity.DeclareReviewEntity;
import com.innovate.modules.declare.service.DeclareReviewService;
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
@RequestMapping("innovate/declare/review")
public class DeclareReviewController extends AbstractController {
    @Autowired
    private DeclareReviewService declareReviewService;
    /**
     * 分配评委组绑定用户
     */
    @PostMapping("/review")
    @RequiresPermissions("innovate:declare:list")
    public R review(@RequestBody Map<String,Object> params){
        declareReviewService.reviewUser(params);
        return R.ok();
    }

    /**
     * 评分
     */
    @PostMapping("/score")
    @RequiresPermissions("innovate:declare:list")
    public R score(@RequestBody(required = false) DeclareReviewEntity declareReviewEntity){
        declareReviewService.score(declareReviewEntity);
        return R.ok();
    }

    /**
     * 查询评分
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:declare:list")
    public R info(@RequestParam Map<String,Object> params){
        DeclareReviewEntity declareReviewEntity = declareReviewService.queryScore(params);
        return R.ok().put("declareReviewEntity",declareReviewEntity);
    }
    /**
     * 查询未评分
     */
    @GetMapping("/unReview")
    @RequiresPermissions("innovate:declare:list")
    public R unReview(@RequestParam Map<String,Object> params){
        PageUtils page = declareReviewService.unReview(params);
        return R.ok().put("page",page);
    }
    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:declare:list")
    public R update(@RequestBody DeclareReviewEntity declareReviewEntity){
        declareReviewService.updateAllColumnById(declareReviewEntity);
        return R.ok();
    }

}
