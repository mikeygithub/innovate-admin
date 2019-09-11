package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntStudentAttachmentEntity;
import com.innovate.modules.enterprise.service.EntStudentAttachmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 学生附件表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@RestController
@RequestMapping("enterprise/student/attachment")
public class EntStudentAttachmentController {
    @Autowired
    private EntStudentAttachmentService entStudentAttachmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:student:attachment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entStudentAttachmentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{stuAttachmentId}")
    @RequiresPermissions("enterprise:student:attachment:info")
    public R info(@PathVariable("stuAttachmentId") Long stuAttachmentId){
		EntStudentAttachmentEntity entStudentAttachment = entStudentAttachmentService.selectById(stuAttachmentId);

        return R.ok().put("entStudentAttachment", entStudentAttachment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:student:attachment:save")
    public R save(@RequestBody EntStudentAttachmentEntity entStudentAttachment){
		entStudentAttachmentService.insert(entStudentAttachment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:student:attachment:update")
    public R update(@RequestBody EntStudentAttachmentEntity entStudentAttachment){
		entStudentAttachmentService.updateById(entStudentAttachment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:student:attachment:delete")
    public R delete(@RequestBody Long[] stuAttachmentIds){
		entStudentAttachmentService.deleteBatchIds(Arrays.asList(stuAttachmentIds));

        return R.ok();
    }

}
