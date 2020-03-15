package com.innovate.modules.match.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.match.entity.MatchRetreatEntity;
import com.innovate.modules.match.service.MatchRetreatService;
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
@RequestMapping("innovate/match/retreat")
public class MatchRetreatController extends AbstractController {

    @Autowired
    private MatchRetreatService matchRetreatService;

    /**
     * 不通过
     */
    @PostMapping("/retreat")
    @RequiresPermissions("innovate:match:retreat")
    public R retreat(@RequestParam Map<String, Object> params) {
        matchRetreatService.updateRetreat(params);
        return R.ok();
    }

    /**
     * 回退修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:match:retreat")
    public R update(@RequestParam Map<String, Object> params) {
        matchRetreatService.updateRetreat(params);
        return R.ok();
    }

    @PostMapping("/query")
    @RequiresPermissions("innovate:match:list")
    public R noPass(@RequestParam Map<String, Object> params){
        List<MatchRetreatEntity> retreatEntityList =  matchRetreatService.queryAll(params);
        return R.ok().put("retreatEntityList",retreatEntityList);
    }
}
