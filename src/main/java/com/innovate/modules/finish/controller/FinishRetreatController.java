package com.innovate.modules.finish.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.finish.entity.FinishRetreatEntity;
import com.innovate.modules.finish.service.FinishRetreatService;
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
@RequestMapping("innovate/finish/retreat")
public class FinishRetreatController extends AbstractController {

    @Autowired
    private FinishRetreatService finishRetreatService;

    /**
     * 不通过
     */
    @PostMapping("/retreat")
    @RequiresPermissions("innovate:finish:retreat")
    public R retreat(@RequestParam Map<String, Object> params) {
        finishRetreatService.updateRetreat(params);
        return R.ok();
    }

    /**
     * 回退修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:finish:retreat")
    public R update(@RequestParam Map<String, Object> params) {
        finishRetreatService.updateRetreat(params);
        return R.ok();
    }

    @PostMapping("/query")
    @RequiresPermissions("innovate:finish:list")
    public R noPass(@RequestParam Map<String, Object> params){
        List<FinishRetreatEntity> retreatEntityList =  finishRetreatService.queryAll(params);
        return R.ok().put("retreatEntityList",retreatEntityList);
    }
}
