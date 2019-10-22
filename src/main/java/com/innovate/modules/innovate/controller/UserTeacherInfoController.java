package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.InnovateInstituteEntity;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.InnovateInstituteService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-09
 * @description:指导老师
 **/
@RestController
@RequestMapping("innovate/use/teacher")
public class UserTeacherInfoController extends AbstractController {
    @Autowired
    private UserTeacherInfoService userTeacherInfoService;
    @Autowired
    private InnovateInstituteService innovateInstituteService;
    @Autowired
    private SysUserService sysUserService;


    /**
     * 教师用户信息
     */
    @GetMapping("/teacher")
    @RequiresPermissions("innovate:project:info")
    public R allTeacher(@RequestParam(required = false) Map<String, Object> params){
        List<SysUserEntity> sysTeacherEntities = userTeacherInfoService.queryTeacher(params);
        return R.ok()
                .put("sysTeacherEntities", sysTeacherEntities);
    }


    /**
     * 所有教师用户信息
     */
    @GetMapping("/all")
    @RequiresPermissions("innovate:project:info")
    public R all(){
        List<UserTeacherInfoEntity> userTeacherInfoEntities = userTeacherInfoService.queryAllTeacherInfo();
        return R.ok()
                .put("userTeacherInfoEntities", userTeacherInfoEntities);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
        UserTeacherInfoEntity userTeacher = userTeacherInfoService.queryByUserId(userId);
        List<InnovateInstituteEntity> institute = innovateInstituteService.queryAllInstitute();
        return R.ok()
                .put("userTeacher",userTeacher)
                .put("institute", institute);
    }

    /**
     * 教师详细信息
     * @return
     */
    @RequestMapping("/teacherInfo/{id}")
    public R teacherInfo(@PathVariable("id") Long id){
        UserTeacherInfoEntity entity = userTeacherInfoService.selectById(id);
        SysUserEntity sysUserEntity = sysUserService.selectById(entity.getUserId());
        entity.setSysUserEntity(sysUserEntity);
        return R.ok().put("data", entity);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody(required = false) UserTeacherInfoEntity userTeacherInfoEntity){
        userTeacherInfoService.saveOrUpdate(userTeacherInfoEntity);
        return R.ok();
    }

}
