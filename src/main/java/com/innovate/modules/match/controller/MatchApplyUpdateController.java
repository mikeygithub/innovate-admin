package com.innovate.modules.match.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.match.entity.MatchApplyUpdateEntity;
import com.innovate.modules.match.service.MatchApplyUpdateService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目申请修改
 **/
@RestController
@RequestMapping("innovate/match/apply/update")
public class MatchApplyUpdateController extends AbstractController {

    @Autowired
    private MatchApplyUpdateService matchApplyUpdateService;


    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:apply:save")
    public R list(@RequestParam Map<String, Object> params) {
        List<MatchApplyUpdateEntity> page = matchApplyUpdateService.queryAll(params);

        return R.ok().put("page", page);
    }

    /**
     * 申请修改
     */
    @PostMapping("/apply")
    @RequiresPermissions("innovate:apply:save")
    public R applyUpdate(@RequestParam Map<String, Object> params) {
        matchApplyUpdateService.applyUpdate(params);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:apply:save")
    public R update(@RequestBody(required = false) MatchApplyUpdateEntity matchApplyUpdateEntity) {
        matchApplyUpdateService.update(matchApplyUpdateEntity);
        return R.ok();
    }
}
