package com.innovate.modules.declare.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.declare.entity.DeclareRetreatEntity;
import com.innovate.modules.declare.service.DeclareRetreatService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
@RestController
@RequestMapping("innovate/declare/retreat")
public class DeclareRetreatController extends AbstractController {

    @Autowired
    private DeclareRetreatService declareRetreatService;

    /**
     * 不通过
     */
    @PostMapping("/retreat")
    @RequiresPermissions("innovate:declare:retreat")
    public R retreat(@RequestParam Map<String, Object> params) {
        declareRetreatService.updateRetreat(params);
        return R.ok();
    }

    /**
     * 回退修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:declare:retreat")
    public R update(@RequestParam Map<String, Object> params) {
        declareRetreatService.updateRetreat(params);
        return R.ok();
    }

    @PostMapping("/query")
    @RequiresPermissions("innovate:declare:list")
    public R noPass(@RequestParam Map<String, Object> params){
        List<DeclareRetreatEntity> retreatEntityList =  declareRetreatService.queryAll(params);
        return R.ok().put("retreatEntityList",retreatEntityList);
    }
}
