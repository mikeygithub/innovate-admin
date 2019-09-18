package com.innovate.modules.check.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innovate.modules.check.entity.InnovateCheckAttachEntity;
import com.innovate.modules.check.service.InnovateCheckAttachService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;



/**
 * 中期检查附件表
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@RestController
@RequestMapping("check/innovatecheckattach")
public class InnovateCheckAttachController {
    @Autowired
    private InnovateCheckAttachService innovateCheckAttachService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("check:innovatecheckattach:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = innovateCheckAttachService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attachId}")
    @RequiresPermissions("check:innovatecheckattach:info")
    public R info(@PathVariable("attachId") Long attachId){
		InnovateCheckAttachEntity innovateCheckAttach = innovateCheckAttachService.selectById(attachId);

        return R.ok().put("innovateCheckAttach", innovateCheckAttach);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("check:innovatecheckattach:save")
    public R save(@RequestBody InnovateCheckAttachEntity innovateCheckAttach){
		innovateCheckAttachService.insert(innovateCheckAttach);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("check:innovatecheckattach:update")
    public R update(@RequestBody InnovateCheckAttachEntity innovateCheckAttach){
		innovateCheckAttachService.updateById(innovateCheckAttach);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("check:innovatecheckattach:delete")
    public R delete(@RequestBody Long[] attachIds){
		innovateCheckAttachService.deleteBatchIds(Arrays.asList(attachIds));

        return R.ok();
    }

}
