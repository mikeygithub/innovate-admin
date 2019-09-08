package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.BaseCostEntity;
import com.innovate.modules.innovate.service.BaseCostService;
import com.innovate.modules.innovate.utils.BaseCalculateUtil;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 兵哥哥
 * @create 2018-11-08 20:35
 * @desc
 **/
@RestController
@RequestMapping("innovate/base/cost")
public class BaseCostController extends AbstractController{

    @Autowired
    private BaseCostService baseCostService;

    private BaseCostEntity tempCostEntity;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:cost:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = baseCostService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{costId}")
    @RequiresPermissions("innovate:cost:info")
    public R info(@PathVariable("costId") Integer costId){
        BaseCostEntity cost = baseCostService.selectById(costId);

        return R.ok().put("cost", cost);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:cost:save")
    public R save(@RequestBody BaseCostEntity baseCostEntity){
        baseCostService.insert(baseCostEntity);
        BaseCalculateUtil.setCalculateId(baseCostEntity.getBaseId());
        BaseCalculateUtil.calculate();

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:cost:update")
    public R update(@RequestBody BaseCostEntity baseCostEntity){
        tempCostEntity = baseCostService.selectById(baseCostEntity.getCostId());
        baseCostService.updateAllColumnById(baseCostEntity);
        BaseCalculateUtil.setCalculateId(baseCostEntity.getBaseId());
        BaseCalculateUtil.calculate();
        BaseCalculateUtil.setCalculateId(tempCostEntity.getBaseId());
        BaseCalculateUtil.calculate();
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:cost:delete")
    public R delete(@RequestBody Long[] costIds){

        for (Long costId: costIds) {
            tempCostEntity = baseCostService.selectById(costId);
            baseCostService.deleteById(costId);
            BaseCalculateUtil.setCalculateId(tempCostEntity.getBaseId());
            BaseCalculateUtil.calculate();
        }

        return R.ok();
    }
}
