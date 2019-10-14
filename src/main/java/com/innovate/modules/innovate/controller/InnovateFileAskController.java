package com.innovate.modules.innovate.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@RestController
@RequestMapping("innovate/sys/file/ask")
public class InnovateFileAskController {
    @Autowired
    private InnovateFileAskService innovateFileAskService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("innovate:file:ask:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = innovateFileAskService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 集合
     */
    @RequestMapping("/query")
    public R queryByParams(@RequestParam Map<String, Object> params){
        InnovateFileAskEntity fileAsk= innovateFileAskService.queryByParams(params);

        return R.ok().put("fileAsk", fileAsk);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fileAskId}")
    @RequiresPermissions("innovate:file:ask:info")
    public R info(@PathVariable("fileAskId") Long fileAskId){
		InnovateFileAskEntity innovateFileAsk = innovateFileAskService.selectById(fileAskId);

        return R.ok().put("innovateFileAsk", innovateFileAsk);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("innovate:file:ask:save")
    public R save(@RequestBody InnovateFileAskEntity innovateFileAsk){

        // 校验同一年度且统一类型的文件要求是否存在
        Map<String, Object> params = new HashMap<>();
        params.put("fileAskType", innovateFileAsk.getFileAskType());
        params.put("fileAskTime", innovateFileAsk.getFileAskTime().getYear()+1900);
        System.out.println(innovateFileAsk.getFileAskTime().getYear()+1900);
        if (innovateFileAskService.queryByParams(params)==null) {
            innovateFileAskService.insert(innovateFileAsk);
        } else {
            System.out.println("###################################################################################");
            return R.error("当前输入的文件类型和年度已存在，不能重复添加！");
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("innovate:file:ask:update")
    public R update(@RequestBody InnovateFileAskEntity innovateFileAsk){
		innovateFileAskService.updateById(innovateFileAsk);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("innovate:file:ask:delete")
    public R delete(@RequestBody Long[] fileAskIds){
		innovateFileAskService.deleteBatchIds(Arrays.asList(fileAskIds));

        return R.ok();
    }

}
