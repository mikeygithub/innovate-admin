package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.InnovateInstituteEntity;
import com.innovate.modules.innovate.service.InnovateInstituteService;
import com.innovate.modules.match.entity.MatchEventEntity;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@RestController
@RequestMapping("innovate/sys/institute")
public class InnovateInstituteController extends AbstractController {

    @Autowired
    private InnovateInstituteService innovateInstituteService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:institute:list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = innovateInstituteService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有学院
     */
    @GetMapping("/all")
    public R all(){
        List<InnovateInstituteEntity> institute = innovateInstituteService.queryAllInstitute();

        return R.ok()
                .put("institute", institute);
    }

    /**
     * 统计各学院申报项目信息
     */
    @PostMapping("/total")
    @RequiresPermissions("innovate:total:save")
    public R total(@RequestParam Map<String, Object> params) {

        if ("{}".equals(params) && params == null) {
            List<Long> instituteIds = new ArrayList<>();
            List<InnovateInstituteEntity> instituteEntityList = innovateInstituteService.queryAllInstitute();
            for (InnovateInstituteEntity innovateInstituteEntity : instituteEntityList) {
                instituteIds.add(innovateInstituteEntity.getInstituteId());
            }
            //封装params
            for (Long instituteId : instituteIds) {
                params.put("instituteId", instituteId);
                //先统计
                innovateInstituteService.total(params);
                params = new HashMap<>();
            }
        }
        //先统计
        innovateInstituteService.total(params);
        //后遍历
        PageUtils page = innovateInstituteService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{instituteId}")
    @RequiresPermissions("innovate:institute:info")
    public R info(@PathVariable("instituteId") Integer instituteId){
        InnovateInstituteEntity institute = innovateInstituteService.selectById(instituteId);

        return R.ok().put("institute", institute);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:institute:save")
    public R save(@RequestBody InnovateInstituteEntity innovateInstituteEntity){
        innovateInstituteService.insert(innovateInstituteEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:institute:update")
    public R update(@RequestBody InnovateInstituteEntity innovateInstituteEntity){
        innovateInstituteService.updateAllColumnById(innovateInstituteEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:institute:delete")
    public R delete(@RequestBody Long[] instituteIds){

        innovateInstituteService.deleteBatchIds(Arrays.asList(instituteIds));

        return R.ok();
    }
}
