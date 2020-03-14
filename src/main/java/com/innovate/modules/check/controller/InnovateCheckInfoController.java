package com.innovate.modules.check.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.innovate.modules.check.entity.InnovateCheckAttachEntity;
import com.innovate.modules.check.entity.InnovateCheckInfoModel;
import com.innovate.modules.check.service.InnovateCheckAttachService;
import com.innovate.modules.check.service.InnovateCheckRetreatService;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.service.DeclareInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("innovate/check")
public class InnovateCheckInfoController {
    @Autowired
    private InnovateCheckInfoService innovateCheckInfoService;
    @Autowired
    private InnovateCheckAttachService innovateCheckAttachService;
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private InnovateCheckRetreatService innovateCheckRetreatService;
    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:check:list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = innovateCheckInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    @RequiresPermissions("innovate:check:info")
    public R info(@RequestParam Map<String, Object> params){

        Long checkId = Long.parseLong(params.get("checkId").toString());
        InnovateCheckInfoModel innovateCheckInfoModel = new InnovateCheckInfoModel();
        InnovateCheckInfoEntity innovateCheckInfo = innovateCheckInfoService.selectById(checkId);
        List<InnovateCheckAttachEntity> innovateCheckAttachEntities = innovateCheckAttachService.queryByCheckId(checkId);
        DeclareInfoEntity declareInfoEntity = declareInfoService.queryById(innovateCheckInfo.getDeclareId());
        innovateCheckInfoModel.setDeclareInfoEntity(declareInfoEntity);
        innovateCheckInfoModel.setInnovateCheckInfoEntity(innovateCheckInfo);
        innovateCheckInfoModel.setInnovateCheckAttachEntities(innovateCheckAttachEntities);

        return R.ok().put("info", innovateCheckInfoModel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("innovate:check:save")
    public R save(@RequestBody(required = false) InnovateCheckInfoModel innovateCheckInfoModel){

        innovateCheckInfoService.save(innovateCheckInfoModel);

        return R.ok();
    }
    /**
     * 设置中期检查项目
     */
    @RequestMapping("/saveByDeclareBatchIds")
    @RequiresPermissions("innovate:check:save")
    public R saveByDeclareBatchIds(@RequestBody Long[] checkIds){

        innovateCheckInfoService.saveByDeclareBatchIds(Arrays.asList(checkIds));

        return R.ok();
    }
    /**
     * 设置中期检查项目
     */
    @RequestMapping("/saveByTime")
    @RequiresPermissions("innovate:check:save")
    public R saveByTime(@RequestParam Map<String, Object> params){

        innovateCheckInfoService.saveByTime(params);

        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("innovate:check:update")
    public R update(@RequestBody(required = false) InnovateCheckInfoModel innovateCheckInfoModel){

		innovateCheckInfoService.updateById(innovateCheckInfoModel.getInnovateCheckInfoEntity());

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("innovate:check:delete")
    public R delete(@RequestBody Long[] checkIds){
		innovateCheckInfoService.deleteBatchIds(Arrays.asList(checkIds));

        return R.ok();
    }

}
