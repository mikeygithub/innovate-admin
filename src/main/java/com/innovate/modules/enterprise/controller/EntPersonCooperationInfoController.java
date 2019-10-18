package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntPersonCooperationInfoEntity;
import com.innovate.modules.enterprise.service.EntPersonCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 项目合作人表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@RestController
@RequestMapping("enterprise/person/cooperation")
public class EntPersonCooperationInfoController {
    @Autowired
    private EntPersonCooperationInfoService entPersonCooperationInfoService;

    @Autowired
    private EntProjectInfoService entProjectInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("enterprise:person:cooperation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entPersonCooperationInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{proInfoId}/{inType}")
    // @RequiresPermissions("enterprise:person:cooperation:info")
    public R info(@PathVariable("proInfoId") Long proInfoId, @PathVariable("inType") String inType){
		// EntPersonCooperationInfoEntity entPersonCooperationInfo = entPersonCooperationInfoService.selectById(proCooperationId);
        //return R.ok().put("entPersonCooperationInfo", entPersonCooperationInfo);
        return entProjectInfoService.queryProjectPersonCooperationInfo(proInfoId, inType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:person:cooperation:save")
    public R save(@RequestBody EntPersonCooperationInfoEntity entPersonCooperationInfo){
		entPersonCooperationInfoService.insert(entPersonCooperationInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:person:cooperation:update")
    public R update(@RequestBody EntPersonCooperationInfoEntity entPersonCooperationInfo){
		entPersonCooperationInfoService.updateById(entPersonCooperationInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:person:cooperation:delete")
    public R delete(@RequestBody Long[] proCooperationIds){
		entPersonCooperationInfoService.deleteBatchIds(Arrays.asList(proCooperationIds));

        return R.ok();
    }

}
