package com.innovate.modules.finish.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innovate.modules.finish.entity.FinishInExpertEntity;
import com.innovate.modules.finish.service.FinishInExpertService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;



/**
 * 
 *
 * @author Mikey
 * @email biaogejiushibiao@outlook.com
 * @date 2019-10-16 22:02:23
 */
@RestController
@RequestMapping("innovate/finish/in-expert")
public class FinishInExpertController {
    @Autowired
    private FinishInExpertService finishInExpertService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("finish:expert:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = finishInExpertService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{expertId}")
    @RequiresPermissions("finish:expert:info")
    public R info(@PathVariable("expertId") Integer expertId){
		FinishInExpertEntity innovateFinishInExpert = finishInExpertService.selectById(expertId);

        return R.ok().put("innovateFinishInExpert", innovateFinishInExpert);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("finish:expert:save")
    public R save(@RequestBody FinishInExpertEntity innovateFinishInExpert){
		finishInExpertService.insert(innovateFinishInExpert);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("finish:expert:update")
    public R update(@RequestBody FinishInExpertEntity innovateFinishInExpert){
		finishInExpertService.updateById(innovateFinishInExpert);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("finish:expert:delete")
    public R delete(@RequestBody Integer[] expertIds){
		finishInExpertService.deleteBatchIds(Arrays.asList(expertIds));

        return R.ok();
    }

}
