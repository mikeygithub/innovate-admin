package com.innovate.modules.check.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.check.entity.InnovateCheckAwardEntity;
import com.innovate.modules.check.entity.InnovateCheckReviewEntity;
import com.innovate.modules.check.service.InnovateCheckAttachService;
import com.innovate.modules.check.service.InnovateCheckAwardService;
import com.innovate.modules.check.service.InnovateCheckReviewService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 21:10
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/check/award")
public class InnovateCheckAwardController extends AbstractController {
    @Autowired
    private InnovateCheckAwardService innovateCheckAwardService;
    /**
     */
    @PostMapping("/list")
    @RequiresPermissions("innovate:check:list")
    public R review(@RequestBody Map<String,Object> params){
        List<InnovateCheckAwardEntity> innovateCheckAwardEntities = innovateCheckAwardService.queryAll(params);
        return R.ok().put("innovateCheckAwardEntities",innovateCheckAwardEntities);
    }

    /**
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:check:list")
    public R score(@RequestBody(required = false) InnovateCheckAwardEntity innovateCheckAwardEntity){
        innovateCheckAwardService.insertOrUpdate(innovateCheckAwardEntity);
        return R.ok();
    }
    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:check:list")
    public R update(@RequestBody InnovateCheckAwardEntity innovateCheckAwardEntity){
        innovateCheckAwardService.updateAllColumnById(innovateCheckAwardEntity);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("innovate:check:list")
    public R delete(@RequestBody Long[] awardIds){
        innovateCheckAwardService.deleteBatchIds(Arrays.asList(awardIds));
        return R.ok();
    }

    /**
     * 查询
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:check:list")
    public R info(@RequestParam Map<String,Object> params){
        long award_id = Long.parseLong(params.get("award_id").toString());
        InnovateCheckAwardEntity checkAwardEntity = innovateCheckAwardService.findByAwardId(award_id);
        return R.ok().put("checkAwardEntity",checkAwardEntity);
    }
}
