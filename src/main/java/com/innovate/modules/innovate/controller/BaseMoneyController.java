package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.BaseMoneyEntity;
import com.innovate.modules.innovate.service.BaseMoneyService;
import com.innovate.modules.innovate.utils.BaseCalculateUtil;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 20:02
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/base/money")
public class BaseMoneyController extends AbstractController {

    @Autowired
    private BaseMoneyService baseMoneyService;

    private BaseMoneyEntity tempBaseMoneyEntity;

    /**
     * 获取收入列表
     * @param params
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:baseMoney:list")
    public R list(@RequestParam Map<String,Object> params){

        PageUtils page= baseMoneyService.queryPage(params);

        return R.ok().put("page",page);
    }

    /**
     * 通过id获取收入详情信息
     * @param baseMoneyId
     * @return
     */
    @GetMapping("/info/{baseMoneyId}")
    @RequiresPermissions("innovate:baseMoney:info")
    public R info(@PathVariable("baseMoneyId") Integer baseMoneyId){
        BaseMoneyEntity baseMoney= baseMoneyService.selectById(baseMoneyId);

        return R.ok().put("baseMoney",baseMoney);
    }

    /**
     * 保存
     * @param baseMoneyEntity
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:baseMoney:save")
    public R save(@RequestBody BaseMoneyEntity baseMoneyEntity){
        baseMoneyService.insert(baseMoneyEntity);

        BaseCalculateUtil.setCalculateId(baseMoneyEntity.getBaseId());
        BaseCalculateUtil.calculate();

        return R.ok();
    }

    /**
     * 修改
     * @param baseMoneyEntity
     * @return
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:baseMoney:update")
    public R update(@RequestBody BaseMoneyEntity baseMoneyEntity){

        //获取修改之前的实体，目的拿到修改前的baseId
        tempBaseMoneyEntity = baseMoneyService.selectById(baseMoneyEntity.getMoneyId());
        baseMoneyService.updateAllColumnById(baseMoneyEntity);

        BaseCalculateUtil.setCalculateId(baseMoneyEntity.getBaseId());
        BaseCalculateUtil.calculate();
        BaseCalculateUtil.setCalculateId(tempBaseMoneyEntity.getBaseId());
        BaseCalculateUtil.calculate();

        return R.ok();
    }

    /**
     * 删除
     * @param baseMoneyIds
     * @return
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:baseMoney:delete")
    public R delete(@RequestBody Long[] baseMoneyIds){
        for (Long baseMoneyId: baseMoneyIds) {
            //获取实体拿到baseId
            tempBaseMoneyEntity = baseMoneyService.selectById(baseMoneyId);
            //删除
            baseMoneyService.deleteById(baseMoneyId);
            BaseCalculateUtil.setCalculateId(tempBaseMoneyEntity.getBaseId());
            BaseCalculateUtil.calculate();
        }
        return R.ok();

    }
}
