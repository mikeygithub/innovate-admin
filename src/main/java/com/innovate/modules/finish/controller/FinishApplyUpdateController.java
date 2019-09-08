package com.innovate.modules.finish.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.finish.entity.FinishApplyUpdateEntity;
import com.innovate.modules.finish.service.FinishApplyUpdateService;
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
@RequestMapping("innovate/finish/apply/update")
public class FinishApplyUpdateController extends AbstractController {

    @Autowired
    private FinishApplyUpdateService finishApplyUpdateService;


    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:apply:save")
    public R list(@RequestParam Map<String, Object> params) {
        List<FinishApplyUpdateEntity> page = finishApplyUpdateService.queryAll(params);

        return R.ok().put("page", page);
    }

    /**
     * 申请修改
     */
    @PostMapping("/apply")
    @RequiresPermissions("innovate:apply:save")
    public R applyUpdate(@RequestParam Map<String, Object> params) {
        finishApplyUpdateService.applyUpdate(params);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:apply:save")
    public R update(@RequestBody(required = false) FinishApplyUpdateEntity finishApplyUpdateEntity) {
        finishApplyUpdateService.update(finishApplyUpdateEntity);
        return R.ok();
    }
}
