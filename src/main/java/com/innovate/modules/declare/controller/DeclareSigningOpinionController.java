package com.innovate.modules.declare.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.declare.entity.DeclareSigningOpinionEntity;
import com.innovate.modules.declare.service.DeclareSigningOpinionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Program: innovate-admin-19-25
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-02-06 00:24
 * @Describe：
 **/
@RestController
@RequestMapping("innovate/declare/signingopinion")
public class DeclareSigningOpinionController {

    @Autowired
    private DeclareSigningOpinionService declareSigningOpinionService;


    /**
     * 添加签署意见
     * @param params
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:signingopinion:save")
    public R addSigningOpinion(@RequestParam Map<String, Object> params){

        declareSigningOpinionService.addSigningOpinion(params);

        return R.ok();
    }

    /**
     * 查询签署意见
     * @param params
     * @return
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:declare:info")
    public R queryByDeclareId(@RequestParam Map<String, Object> params){

        return R.ok().put("sighingOpinion",declareSigningOpinionService.queryDeclareSigningOpinionByDeclareId(params));

    }

    /**签署意见
     * 删除
     * @param params
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:signingopinion:delete")
    public R remove(@RequestParam Map<String, Object> params){

        declareSigningOpinionService.remove(params);

        return R.ok();

    }


}
