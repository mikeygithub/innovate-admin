package com.innovate.modules.finish.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.finish.entity.FinishReviewEntity;
import com.innovate.modules.finish.service.FinishReviewService;
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
@RequestMapping("innovate/finish/review")
public class FinishReviewController extends AbstractController {
    @Autowired
    private FinishReviewService finishReviewService;
    /**
     * 分配评委组绑定用户
     */
    @PostMapping("/review")
    @RequiresPermissions("innovate:finish:list")
    public R review(@RequestBody Map<String,Object> params){
        finishReviewService.reviewUser(params);
        return R.ok();
    }

    /**
     * 评分
     */
    @PostMapping("/score")
    @RequiresPermissions("innovate:finish:list")
    public R score(@RequestBody(required = false) FinishReviewEntity finishReviewEntity){
        finishReviewService.score(finishReviewEntity);
        return R.ok();
    }

    /**
     * 查询评分
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:finish:list")
    public R info(@RequestParam Map<String,Object> params){
        FinishReviewEntity finishReviewEntity = finishReviewService.queryScore(params);
        return R.ok().put("finishReviewEntity",finishReviewEntity);
    }
    /**
     * 查询未评分
     */
    @GetMapping("/unReview")
    @RequiresPermissions("innovate:finish:list")
    public R unReview(@RequestParam Map<String,Object> params){
        PageUtils page = finishReviewService.unReview(params);
        return R.ok().put("page",page);
    }
    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:finish:list")
    public R update(@RequestBody FinishReviewEntity finishReviewEntity){
        finishReviewService.updateAllColumnById(finishReviewEntity);
        return R.ok();
    }

}
