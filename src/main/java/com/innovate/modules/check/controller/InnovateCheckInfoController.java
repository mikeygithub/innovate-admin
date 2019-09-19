package com.innovate.modules.check.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innovate.modules.check.entity.InnovateCheckInfoEntity;
import com.innovate.modules.check.service.InnovateCheckInfoService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;



/**
 * 中期检查表
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@RestController
@RequestMapping("check/innovatecheckinfo")
public class InnovateCheckInfoController {
    @Autowired
    private InnovateCheckInfoService innovateCheckInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("check:innovatecheckinfo:list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = innovateCheckInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{checkId}")
    @RequiresPermissions("check:innovatecheckinfo:info")
    public R info(@PathVariable("checkId") Long checkId){

		InnovateCheckInfoEntity innovateCheckInfo = innovateCheckInfoService.selectById(checkId);

        return R.ok().put("innovateCheckInfo", innovateCheckInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("check:innovatecheckinfo:save")
    public R save(@RequestBody InnovateCheckInfoEntity innovateCheckInfo){
		innovateCheckInfoService.insert(innovateCheckInfo);

        return R.ok();
    }
    /**
     * 设置中期检查项目
     */
    @RequestMapping("/saveByDeclareBatchIds")
    @RequiresPermissions("check:innovatecheckinfo:save")
    public R saveByDeclareBatchIds(@RequestBody Long[] checkIds){

        innovateCheckInfoService.saveByDeclareBatchIds(checkIds);

        return R.ok();
    }
    /**
     * 设置中期检查项目
     */
    @RequestMapping("/saveByTime")
    @RequiresPermissions("check:innovatecheckinfo:save")
    public R saveByTime(@RequestBody Date time){

        innovateCheckInfoService.saveByTime(time);

        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("check:innovatecheckinfo:update")
    public R update(@RequestBody InnovateCheckInfoEntity innovateCheckInfo){
		innovateCheckInfoService.updateById(innovateCheckInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("check:innovatecheckinfo:delete")
    public R delete(@RequestBody Long[] checkIds){
		innovateCheckInfoService.deleteBatchIds(Arrays.asList(checkIds));

        return R.ok();
    }

}
