package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.BaseProjectStationEntity;
import com.innovate.modules.innovate.service.BaseProjectStationService;
import com.innovate.modules.innovate.utils.BaseCalculateUtil;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:工位信息
 **/
@RestController
@RequestMapping("innovate/base/station")
public class BaseProjectStationController extends AbstractController {

    @Autowired
    private BaseProjectStationService stationService;

    private BaseProjectStationEntity tempProjectStationEntity;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:station:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = stationService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 所有列表
     */
    @GetMapping("/all")
    @RequiresPermissions("innovate:station:list")
    public R allList(@RequestParam Map<String, Object> params){
        List<BaseProjectStationEntity> baseProjectStationEntities = stationService.queryAll();
        return R.ok().put("baseProjectStationEntities", baseProjectStationEntities);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{stationId}")
    @RequiresPermissions("innovate:station:info")
    public R info(@PathVariable("stationId") Integer stationId){
        BaseProjectStationEntity station = stationService.selectById(stationId);

        return R.ok().put("station", station);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:station:save")
    public R save(@RequestBody BaseProjectStationEntity stationEntity){
        stationService.insert(stationEntity);
        BaseCalculateUtil.setCalculateId(stationEntity.getBaseId());
        BaseCalculateUtil.calculate();

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:station:update")
    public R update(@RequestBody BaseProjectStationEntity stationEntity){
        tempProjectStationEntity = stationService.selectById(stationEntity.getStationId());
        stationService.updateAllColumnById(stationEntity);
        BaseCalculateUtil.setCalculateId(stationEntity.getBaseId());
        BaseCalculateUtil.calculate();
        BaseCalculateUtil.setCalculateId(tempProjectStationEntity.getBaseId());
        BaseCalculateUtil.calculate();
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:station:delete")
    public R delete(@RequestBody Long[] stationIds){
        for (Long stationId: stationIds) {
            tempProjectStationEntity = stationService.selectById(stationId);
            stationService.deleteById(stationId);
            BaseCalculateUtil.setCalculateId(tempProjectStationEntity.getBaseId());
            BaseCalculateUtil.calculate();
        }
        return R.ok();
    }
}
