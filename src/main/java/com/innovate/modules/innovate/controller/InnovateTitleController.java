package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.InnovateSubjectEntity;
import com.innovate.modules.innovate.entity.InnovateTitleEntity;
import com.innovate.modules.innovate.service.InnovateSubjectService;
import com.innovate.modules.innovate.service.InnovateTitleService;
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
@RequestMapping("innovate/sys/title")
public class InnovateTitleController extends AbstractController {

    @Autowired
    private InnovateTitleService innovateTitleService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:title:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = innovateTitleService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 职称信息
     */
    @GetMapping("/title")
//    @RequiresPermissions("innovate:title:list")
    public R allTitle(@RequestParam Map<String, Object> params){
        List<InnovateTitleEntity> innovateTitleEntities = innovateTitleService.queryTitle(params);
        return R.ok()
                .put("innovateTitleEntities", innovateTitleEntities);
    }

    /**
     * 所有
     */
    @GetMapping("/all")
    public R all(){
        List<InnovateTitleEntity> title = innovateTitleService.queryAll();

        return R.ok()
                .put("title", title);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{titleIds}")
    @RequiresPermissions("innovate:title:info")
    public R info(@PathVariable("titleIds") Integer titleIds){
        InnovateTitleEntity title = innovateTitleService.selectById(titleIds);

        return R.ok().put("title", title);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:title:save")
    public R save(@RequestBody InnovateTitleEntity innovateTitleEntity){
        innovateTitleService.insert(innovateTitleEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:title:update")
    public R update(@RequestBody InnovateTitleEntity innovateTitleEntity){
        innovateTitleService.updateAllColumnById(innovateTitleEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:title:delete")
    public R delete(@RequestBody Long[] titleIds){

        innovateTitleService.deleteBatchIds(Arrays.asList(titleIds));

        return R.ok();
    }
}
