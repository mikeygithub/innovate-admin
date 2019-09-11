package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntTeacherAttachmentEntity;
import com.innovate.modules.enterprise.service.EntTeacherAttachmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 教师附件表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@RestController
@RequestMapping("enterprise/teacher/attachment")
public class EntTeacherAttachmentController {
    @Autowired
    private EntTeacherAttachmentService entTeacherAttachmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:teacher:attachment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entTeacherAttachmentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{teaAttachmentId}")
    @RequiresPermissions("enterprise:teacher:attachment:info")
    public R info(@PathVariable("teaAttachmentId") Long teaAttachmentId){
		EntTeacherAttachmentEntity entTeacherAttachment = entTeacherAttachmentService.selectById(teaAttachmentId);

        return R.ok().put("entTeacherAttachment", entTeacherAttachment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:teacher:attachment:save")
    public R save(@RequestBody EntTeacherAttachmentEntity entTeacherAttachment){
		entTeacherAttachmentService.insert(entTeacherAttachment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:teacher:attachment:update")
    public R update(@RequestBody EntTeacherAttachmentEntity entTeacherAttachment){
		entTeacherAttachmentService.updateById(entTeacherAttachment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:teacher:attachment:delete")
    public R delete(@RequestBody Long[] teaAttachmentIds){
		entTeacherAttachmentService.deleteBatchIds(Arrays.asList(teaAttachmentIds));

        return R.ok();
    }

}
