package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.InnovateGradeEntity;
import com.innovate.modules.innovate.service.InnovateGradeService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@RestController
@RequestMapping("innovate/sys/grade")
public class InnovateGradeController extends AbstractController {

    @Autowired
    private InnovateGradeService innovateGradeService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:grade:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = innovateGradeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有
     */
    @GetMapping("/all")
    public R all(){
        List<InnovateGradeEntity> grade = innovateGradeService.queryAllGrade();

        return R.ok()
                .put("grade", grade);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{gradeId}")
    @RequiresPermissions("innovate:grade:info")
    public R info(@PathVariable("gradeId") Integer gradeId){
        InnovateGradeEntity grade = innovateGradeService.selectById(gradeId);

        return R.ok().put("grade", grade);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:grade:save")
    public R save(@RequestBody InnovateGradeEntity innovateGradeEntity){
        innovateGradeService.insert(innovateGradeEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:grade:update")
    public R update(@RequestBody InnovateGradeEntity innovateGradeEntity){
        innovateGradeService.updateAllColumnById(innovateGradeEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:grade:delete")
    public R delete(@RequestBody Long[] gradeIds){

        innovateGradeService.deleteBatchIds(Arrays.asList(gradeIds));

        return R.ok();
    }
}
