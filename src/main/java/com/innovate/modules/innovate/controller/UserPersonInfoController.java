package com.innovate.modules.innovate.controller;

import com.google.gson.Gson;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.InnovateGradeEntity;
import com.innovate.modules.innovate.entity.InnovateInstituteEntity;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.service.InnovateGradeService;
import com.innovate.modules.innovate.service.InnovateInstituteService;
import com.innovate.modules.innovate.service.UserPerInfoService;
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
 * @description:责任人
 **/
@RestController
@RequestMapping("innovate/use/person")
public class UserPersonInfoController extends AbstractController {
    @Autowired
    private UserPerInfoService userPerInfoService;
    @Autowired
    private InnovateInstituteService innovateInstituteService;
    @Autowired
    private InnovateGradeService innovateGradeService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取信息
     */
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
        UserPersonInfoEntity userPerson = userPerInfoService.queryByUserId(userId);
        List<InnovateInstituteEntity> institute = innovateInstituteService.queryAllInstitute();
        List<InnovateGradeEntity> grade = innovateGradeService.queryAllGrade();
        return R.ok()
                .put("userPerson",userPerson)
                .put("institute", institute)
                .put("grade", grade);
    }

    /**
     * 学生详细信息
     * @return
     */
    @RequestMapping("/perInfo/{id}")
    public R perInfo(@PathVariable("id") Long id){
        UserPersonInfoEntity entity = userPerInfoService.selectById(id);
        SysUserEntity sysUserEntity = sysUserService.selectById(entity.getUserId());
        entity.setSysUserEntity(sysUserEntity);
        return R.ok().put("data", entity);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody(required = false) UserPersonInfoEntity userPersonInfoEntity){
        System.out.println("这是" + new Gson().toJson(userPersonInfoEntity));;
        userPerInfoService.saveOrUpdate(userPersonInfoEntity);
        return R.ok();
    }

}
