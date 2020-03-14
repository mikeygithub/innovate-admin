package com.innovate.modules.finish.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.innovate.modules.finish.entity.FinishInExpertEntity;
import com.innovate.modules.finish.entity.FinishOutExpertEntity;
import com.innovate.modules.finish.service.FinishInExpertService;
import com.innovate.modules.finish.service.FinishOutExpertService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innovate.modules.finish.entity.FinishExpertCollectEntity;
import com.innovate.modules.finish.service.FinishExpertCollectService;
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
@RequestMapping("innovate/finish/expert")
public class FinishExpertCollectController {
    @Autowired
    private FinishExpertCollectService finishExpertCollectService;
    @Autowired
    private FinishInExpertService finishInExpertService;
    @Autowired
    private FinishOutExpertService finishOutExpertService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("finish:expert:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = finishExpertCollectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{expertCollectId}")
    @RequiresPermissions("finish:expert:info")
    public R info(@PathVariable("expertCollectId") Integer expertCollectId){

		FinishExpertCollectEntity innovateFinishExpertCollect = finishExpertCollectService.selectById(expertCollectId);
        //详情
        innovateFinishExpertCollect.setFinishOutExpertEntities(finishOutExpertService.findByFinishExpertCollectId(expertCollectId));
        innovateFinishExpertCollect.setFinishInExpertEntities(finishInExpertService.findByFinishExpertCollectId(expertCollectId));

        return R.ok().put("innovateFinishExpertCollect", innovateFinishExpertCollect);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("finish:expert:save")
    public R save(@RequestBody FinishExpertCollectEntity innovateFinishExpertCollect){

		finishExpertCollectService.insert(innovateFinishExpertCollect);

        List<FinishInExpertEntity> in = innovateFinishExpertCollect.getFinishInExpertEntities();
        List<FinishOutExpertEntity> out = innovateFinishExpertCollect.getFinishOutExpertEntities();

        for (int i = 0; i < in.size(); i++) {
            in.get(i).setExpertCollectId(innovateFinishExpertCollect.getExpertCollectId());
        }

        for (int i = 0; i < out.size(); i++) {
            out.get(i).setExpertCollectId(innovateFinishExpertCollect.getExpertCollectId());
        }

        finishInExpertService.insertBatch(in);
        finishOutExpertService.insertBatch(out);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("finish:expert:update")
    public R update(@RequestBody FinishExpertCollectEntity innovateFinishExpertCollect){

		finishExpertCollectService.updateByProps(innovateFinishExpertCollect);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("finish:expert:delete")
    public R delete(@RequestBody Integer[] expertCollectIds){

		finishExpertCollectService.deleteBatchIds(Arrays.asList(expertCollectIds));

        return R.ok();
    }

    @RequestMapping("/apply")
    public R apply(@RequestParam Map<String, Object> params){

        int status = Integer.parseInt(params.get("status").toString());

        String expertCollectId = params.get("expertCollectId").toString();

        FinishExpertCollectEntity finishExpertCollectEntity = finishExpertCollectService.selectById(expertCollectId);

        finishExpertCollectEntity.setExpertApply(status);

        finishExpertCollectService.updateAllColumnById(finishExpertCollectEntity);

        return R.ok();
    }

}
