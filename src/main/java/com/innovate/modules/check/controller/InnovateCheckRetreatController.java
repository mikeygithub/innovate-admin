package com.innovate.modules.check.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.innovate.modules.check.entity.InnovateCheckRetreatEntity;
import com.innovate.modules.check.service.InnovateCheckRetreatService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;



/**
 * 中期检查回退
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@RestController
@RequestMapping("innovate/check/retreat")
public class InnovateCheckRetreatController {
    @Autowired
    private InnovateCheckRetreatService innovateCheckRetreatService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("check:retreat:list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = innovateCheckRetreatService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{retreatId}")
    @RequiresPermissions("check:retreat:info")
    public R info(@PathVariable("retreatId") Long retreatId){
		InnovateCheckRetreatEntity innovateCheckRetreat = innovateCheckRetreatService.selectById(retreatId);

        return R.ok().put("innovateCheckRetreat", innovateCheckRetreat);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("check:retreat:save")
    public R save(@RequestParam Map<String, Object> params){

        innovateCheckRetreatService.retreat(params);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("check:retreat:update")
    public R update(@RequestBody InnovateCheckRetreatEntity innovateCheckRetreat){
		innovateCheckRetreatService.updateById(innovateCheckRetreat);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("check:retreat:delete")
    public R delete(@RequestBody Long[] retreatIds){
		innovateCheckRetreatService.deleteBatchIds(Arrays.asList(retreatIds));

        return R.ok();
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    @RequiresPermissions("check:retreat:list")
    public R query(@RequestParam Map<String, Object> params){

        List<InnovateCheckRetreatEntity> query = innovateCheckRetreatService.query(params);

        return R.ok().put("retreatEntityList",query);
    }
}
