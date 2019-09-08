package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.InnovateSchoolEntity;
import com.innovate.modules.innovate.service.InnovateSchoolService;
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
@RequestMapping("innovate/sys/school")
public class InnovateSchoolController extends AbstractController {

    @Autowired
    private InnovateSchoolService innovateSchoolService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:school:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = innovateSchoolService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有
     */
    @GetMapping("/all")
    public R all(){
        List<InnovateSchoolEntity> school = innovateSchoolService.queryAll();

        return R.ok()
                .put("school", school);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{schoolId}")
    @RequiresPermissions("innovate:school:info")
    public R info(@PathVariable("schoolId") Integer schoolId){
        InnovateSchoolEntity school = innovateSchoolService.selectById(schoolId);

        return R.ok().put("school", school);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:school:save")
    public R save(@RequestBody InnovateSchoolEntity innovateSchoolEntity){
        innovateSchoolService.insert(innovateSchoolEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:school:update")
    public R update(@RequestBody InnovateSchoolEntity innovateSchoolEntity){
        innovateSchoolService.updateAllColumnById(innovateSchoolEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:school:delete")
    public R delete(@RequestBody Long[] schoolIds){

        innovateSchoolService.deleteBatchIds(Arrays.asList(schoolIds));

        return R.ok();
    }
}
