package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.InnovateReviewGroupEntity;
import com.innovate.modules.innovate.service.InnovateReviewGroupService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@RestController
@RequestMapping("innovate/sys/group")
public class InnovateReviewGroupController extends AbstractController {

    @Autowired
    private InnovateReviewGroupService innovateReviewGroupService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:group:list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = innovateReviewGroupService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有列表
     */
    @GetMapping("/all")
    @RequiresPermissions("innovate:project:list")
    public R all(){
        List<InnovateReviewGroupEntity> innovateReviewGroupEntity = innovateReviewGroupService.queryAllGroup();
        return R.ok().put("innovateReviewGroupEntity", innovateReviewGroupEntity);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{groupId}")
    @RequiresPermissions("innovate:group:info")
    public R info(@PathVariable("groupId") Long groupId){
        InnovateReviewGroupEntity group = innovateReviewGroupService.queryById(groupId);

        return R.ok().put("group", group);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:group:save")
    public R save(@RequestBody InnovateReviewGroupEntity innovateReviewGroupEntity){
        innovateReviewGroupService.save(innovateReviewGroupEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:group:update")
    public R update(@RequestBody InnovateReviewGroupEntity innovateReviewGroupEntity){
        innovateReviewGroupService.update(innovateReviewGroupEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:group:delete")
    public R delete(@RequestBody Long[] groupIds){

        for (Long groupId: groupIds) {
            innovateReviewGroupService.deleteById(groupId);
        }

        return R.ok();
    }
}
