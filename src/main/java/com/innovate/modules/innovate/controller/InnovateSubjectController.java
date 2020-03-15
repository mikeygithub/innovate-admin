package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.InnovateSchoolEntity;
import com.innovate.modules.innovate.entity.InnovateSubjectEntity;
import com.innovate.modules.innovate.service.InnovateSchoolService;
import com.innovate.modules.innovate.service.InnovateSubjectService;
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
@RequestMapping("innovate/sys/subject")
public class InnovateSubjectController extends AbstractController {

    @Autowired
    private InnovateSubjectService innovateSubjectService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:subject:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = innovateSubjectService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有
     */
    @GetMapping("/all")
    public R all(){
        List<InnovateSubjectEntity> subject = innovateSubjectService.queryAll();

        return R.ok()
                .put("subject", subject);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{subjectId}")
    @RequiresPermissions("innovate:subject:info")
    public R info(@PathVariable("subjectId") Integer subjectId){
        InnovateSubjectEntity subject = innovateSubjectService.selectById(subjectId);

        return R.ok().put("subject", subject);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:subject:save")
    public R save(@RequestBody InnovateSubjectEntity innovateSubjectEntity){
        innovateSubjectService.insert(innovateSubjectEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:subject:update")
    public R update(@RequestBody InnovateSubjectEntity innovateSubjectEntity){
        innovateSubjectService.updateAllColumnById(innovateSubjectEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:subject:delete")
    public R delete(@RequestBody Long[] schoolIds){

        innovateSubjectService.deleteBatchIds(Arrays.asList(schoolIds));

        return R.ok();
    }
}
