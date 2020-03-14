package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;
import com.innovate.modules.innovate.service.InnovateReviewGroupUserService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@RestController
@RequestMapping("innovate/sys/group/user")
public class InnovateReviewGroupUserController extends AbstractController {

    @Autowired
    private InnovateReviewGroupUserService innovateReviewGroupUserService;

    /**
     * 信息
     */
    @GetMapping("/info/{gradeId}")
    @RequiresPermissions("innovate:group:info")
    public R info(@PathVariable("gradeId") Integer gradeId){
        InnovateReviewGroupUserEntity grade = innovateReviewGroupUserService.selectById(gradeId);
        return R.ok().put("grade", grade);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:group:save")
    public R save(@RequestBody InnovateReviewGroupUserEntity innovateGroupUserEntity){
        innovateReviewGroupUserService.insert(innovateGroupUserEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:group:update")
    public R update(@RequestBody InnovateReviewGroupUserEntity innovateGroupUserEntity){
        innovateReviewGroupUserService.updateAllColumnById(innovateGroupUserEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:group:delete")
    public R delete(@RequestBody Long[] gradeIds){
        innovateReviewGroupUserService.deleteBatchIds(Arrays.asList(gradeIds));
        return R.ok();
    }
}
