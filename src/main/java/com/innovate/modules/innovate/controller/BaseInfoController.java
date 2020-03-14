package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.BaseInfoEntity;
import com.innovate.modules.innovate.service.BaseInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 20:54
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/base/info")
public class BaseInfoController extends AbstractController {

    @Autowired
    private BaseInfoService baseInfoService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:baseInfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = baseInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 通过id获取详细信息
     * @param baseInfoId
     * @return
     */
    @GetMapping("/info/{baseInfoId}")
    @RequiresPermissions("innovate:baseInfo:info")
    public R info(@PathVariable("baseInfoId") Integer baseInfoId){
        BaseInfoEntity baseInfo = baseInfoService.selectById(baseInfoId);

        return R.ok().put("baseInfo", baseInfo);
    }

    /**
     * 保存
     * @param innovateBaseInfo
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:baseInfo:save")
    public R save(@RequestBody BaseInfoEntity innovateBaseInfo){
        baseInfoService.insert(innovateBaseInfo);

        return R.ok();
    }

    /**
     * 修改
     * @param innovateBaseInfo
     * @return
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:baseInfo:update")
    public R update(@RequestBody BaseInfoEntity innovateBaseInfo){
        BaseInfoEntity baseInfo = baseInfoService.selectById(innovateBaseInfo.getBaseId());
        baseInfo.setBaseName(innovateBaseInfo.getBaseName());
        baseInfo.setBaseMessage(innovateBaseInfo.getBaseMessage());
        baseInfoService.updateAllColumnById(baseInfo);
        return R.ok();
    }

    /**
     * 删除
     * @param baseInfoIds
     * @return
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:baseInfo:delete")
    public R delete(@RequestBody Long[] baseInfoIds){

        baseInfoService.deleteBatchIds(Arrays.asList(baseInfoIds));

        return R.ok();
    }
}
