package com.innovate.modules.enterprise.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innovate.modules.enterprise.entity.EntProjectMemberEntity;
import com.innovate.modules.enterprise.service.EntProjectMemberService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;



/**
 * 项目合作成员
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-11-09 14:05:55
 */
@RestController
@RequestMapping("enterprise/entprojectmember")
public class EntProjectMemberController {
    @Autowired
    private EntProjectMemberService entProjectMemberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:entprojectmember:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entProjectMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{proMemberId}")
    @RequiresPermissions("enterprise:entprojectmember:info")
    public R info(@PathVariable("proMemberId") Long proMemberId){
		EntProjectMemberEntity entProjectMember = entProjectMemberService.selectById(proMemberId);

        return R.ok().put("entProjectMember", entProjectMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:entprojectmember:save")
    public R save(@RequestBody EntProjectMemberEntity entProjectMember){
		entProjectMemberService.insert(entProjectMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:entprojectmember:update")
    public R update(@RequestBody EntProjectMemberEntity entProjectMember){
		entProjectMemberService.updateById(entProjectMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:entprojectmember:delete")
    public R delete(@RequestBody Long[] proMemberIds){
		entProjectMemberService.deleteBatchIds(Arrays.asList(proMemberIds));

        return R.ok();
    }

}
