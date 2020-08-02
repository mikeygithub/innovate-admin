package com.innovate.modules.match.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.match.entity.MatchReviewEntity;
import com.innovate.modules.match.service.MatchReviewService;
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
@RequestMapping("innovate/match/review")
public class MatchReviewController extends AbstractController {
    @Autowired
    private MatchReviewService matchReviewService;
    /**
     * 分配评委组绑定用户
     */
    @PostMapping("/review")
    @RequiresPermissions("innovate:match:list")
    public R review(@RequestBody Map<String,Object> params){
        matchReviewService.reviewUser(params);
        return R.ok();
    }

    /**
     * 评分
     */
    @PostMapping("/score")
    @RequiresPermissions("innovate:match:list")
    public R score(@RequestBody(required = false) MatchReviewEntity matchReviewEntity){
        matchReviewService.score(matchReviewEntity);
        return R.ok();
    }

    /**
     * 查询评分
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:match:list")
    public R info(@RequestParam Map<String,Object> params){
        MatchReviewEntity matchReviewEntity = matchReviewService.queryScore(params);
        return R.ok().put("matchReviewEntity",matchReviewEntity);
    }
    /**
     * 查询未评分
     */
    @GetMapping("/unReview")
    @RequiresPermissions("innovate:declare:list")
    public R unReview(@RequestParam Map<String,Object> params){
        PageUtils page = matchReviewService.unReview(params);
        return R.ok().put("page",page);
    }
    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:match:list")
    public R update(@RequestBody MatchReviewEntity matchReviewEntity){
        matchReviewService.updateAllColumnById(matchReviewEntity);
        return R.ok();
    }

}
