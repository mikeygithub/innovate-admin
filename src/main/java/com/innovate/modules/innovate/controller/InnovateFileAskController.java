package com.innovate.modules.innovate.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innovate.modules.innovate.entity.InnovateFileAskEntity;
import com.innovate.modules.innovate.service.InnovateFileAskService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;



/**
 * 上传文件要求表
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@RestController
@RequestMapping("check/innovatefileask")
public class InnovateFileAskController {
    @Autowired
    private InnovateFileAskService innovateFileAskService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("check:innovatefileask:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = innovateFileAskService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fileAskId}")
    @RequiresPermissions("check:innovatefileask:info")
    public R info(@PathVariable("fileAskId") Long fileAskId){
		InnovateFileAskEntity innovateFileAsk = innovateFileAskService.selectById(fileAskId);

        return R.ok().put("innovateFileAsk", innovateFileAsk);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("check:innovatefileask:save")
    public R save(@RequestBody InnovateFileAskEntity innovateFileAsk){
		innovateFileAskService.insert(innovateFileAsk);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("check:innovatefileask:update")
    public R update(@RequestBody InnovateFileAskEntity innovateFileAsk){
		innovateFileAskService.updateById(innovateFileAsk);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("check:innovatefileask:delete")
    public R delete(@RequestBody Long[] fileAskIds){
		innovateFileAskService.deleteBatchIds(Arrays.asList(fileAskIds));

        return R.ok();
    }

}
