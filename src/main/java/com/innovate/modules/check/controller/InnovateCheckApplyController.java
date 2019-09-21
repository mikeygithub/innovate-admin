package com.innovate.modules.check.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.check.service.InnovateCheckInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName innovate-admin
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 9/21/19 8:50 PM
 * @Version 1.0
 * @Description:
 **/
@RestController
@RequestMapping("innovate/check")
public class InnovateCheckApplyController {

    @Autowired
    private InnovateCheckInfoService innovateCheckInfoService;

    @RequestMapping("/apply")
    @RequiresPermissions("innovate:check:info")
    public R apply(@RequestParam Map<String, Object> params){
        innovateCheckInfoService.apply(params);
        return R.ok();
    }
}
