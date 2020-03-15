package com.innovate.modules.declare.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.declare.entity.DeclareApplyUpdateEntity;
import com.innovate.modules.declare.service.DeclareApplyUpdateService;
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
@RequestMapping("innovate/declare/apply/update")
public class DeclareApplyUpdateController extends AbstractController {

    @Autowired
    private DeclareApplyUpdateService declareApplyUpdateService;


    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:apply:save")
    public R list(@RequestParam Map<String, Object> params) {
        List<DeclareApplyUpdateEntity> page = declareApplyUpdateService.queryAll(params);

        return R.ok().put("page", page);
    }

    /**
     * 申请修改
     */
    @PostMapping("/apply")
    @RequiresPermissions("innovate:apply:save")
    public R applyUpdate(@RequestParam Map<String, Object> params) {
        declareApplyUpdateService.applyUpdate(params);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:apply:save")
    public R update(@RequestBody(required = false) DeclareApplyUpdateEntity declareApplyUpdateEntity) {
        declareApplyUpdateService.update(declareApplyUpdateEntity);
        return R.ok();
    }
}
