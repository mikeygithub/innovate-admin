package com.innovate.modules.match.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.match.service.MatchApplyService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Mikey
 * @Title: 审核
 * @Description:
 * @date 2018/11/8 21:10
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/match/apply")
public class MatchApplyController extends AbstractController {
    @Autowired
    private MatchApplyService matchApplyService;

    /**
     * 审核（状态修改）
     * @param
     * @return
     */
    @PostMapping("/apply")
    @RequiresPermissions("innovate:match:info")
    public R apply(@RequestParam Map<String, Object> params){
        matchApplyService.apply(params);
        return R.ok();
    }

}
