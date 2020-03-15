package com.innovate.modules.declare.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.declare.service.DeclareApplyService;
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
@RequestMapping("innovate/declare/apply")
public class DeclareApplyController extends AbstractController {
    @Autowired
    private DeclareApplyService declareApplyService;

    /**
     * 审核（状态修改）
     * @param
     * @return
     */
    @PostMapping("/apply")
    @RequiresPermissions("innovate:declare:info")
    public R apply(@RequestParam Map<String, Object> params){
        declareApplyService.apply(params);
        return R.ok();
    }

}
