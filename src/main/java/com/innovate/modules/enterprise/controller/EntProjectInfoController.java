package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.HasAdminRole;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import com.innovate.modules.enterprise.service.EntPersonCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 项目信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@RestController
@RequestMapping("enterprise/project/info")
public class EntProjectInfoController extends AbstractController {
    @Autowired
    private EntProjectInfoService entProjectInfoService;

    @Autowired
    private EntProjectCooperationInfoService entProjectCooperationInfoService;

    @Autowired
    private EntPersonCooperationInfoService entPersonCooperationInfoService;

    /**
     * 列表
     */
    @HasAdminRole(
            targetType = java.util.Map.class,
            index = 0, roleIds = {"9","10"}, perRoleId = "11",
            perRoleKey = "user_per_id", teacherRoleId = "12",
            teacherRoleKey = "user_teacher_id", entRoleId = "7",entRoleKey = "ent_info_id"
    )
    @RequestMapping("/list")
    // @RequiresPermissions("enterprise:project:info:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entProjectInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 该方法用于查询项目信息+附件信息+合作信息+负责人信息+成员信息+专利
     * @return
     */
    @RequestMapping("/info/{inType}/{proInfoId}")
    public R projectInfo(@PathVariable("proInfoId") Long proInfoId, @PathVariable("inType") String inType){
        return entProjectInfoService.queryProjectInfoByIdAndType(proInfoId, inType);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{hasType}/{proInfoId}")
    //@RequiresPermissions("enterprise:project:info")
    public R info(@PathVariable("proInfoId") Long proInfoId, @PathVariable("hasType") String hasType){
        if("userPerId".equals(hasType)){ // 学生
            return entProjectInfoService.queryEntProjectInfoByIdPerId(proInfoId);
        }else if("userTeacherId".equals(hasType)){ // 教师
            return entProjectInfoService.queryEntProjectInfoByIdTeacherId(proInfoId);
        }else if ("entInfoId".equals(hasType)){ // 企业
            return entProjectInfoService.queryEntProjectInfoByIdEntId(proInfoId);
        }
        return R.ok();
    }

    /**
     * 查询用户对应的项目信息
     * @return
     */
    @RequestMapping("/queryPeojects")
    public R queryPeojects(){
        return entProjectInfoService.queryPeojects();
    }

    /**
     * 处理项目信息审核
     * @return
     */
    @RequestMapping("/entExamine")
    public R entExamine(@RequestParam  Map<String, Object> params){
        logger.info("接收数据:{}", params);
        return entProjectInfoService.updateEntExamine(params);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("enterprise:project:info:save")
    public R save(@RequestBody EntProjectInfoEntity entProjectInfo){
        return entProjectInfoService.insertEntProject(entProjectInfo);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:project:info:update")
    public R update(@RequestBody EntProjectInfoEntity entProjectInfo){
		entProjectInfoService.updateById(entProjectInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @Transactional
    @RequestMapping("/delete")
    //@RequiresPermissions("enterprise:project:info:delete")
    public R delete(@RequestBody Long[] proInfoIds){
        List<Long> projectIds = Arrays.asList(proInfoIds);
        entProjectInfoService.deleteBatchIds(projectIds);
        for(Long proInfoId : projectIds){
            EntProjectCooperationInfoEntity cooperationInfo = entProjectCooperationInfoService.queryEntProjectCooperationInfoByProjectId(proInfoId);
            entProjectCooperationInfoService.deleteByProInfoId(proInfoId);
            if(cooperationInfo != null) {
                entPersonCooperationInfoService.deleteByProCooperationInfoId(cooperationInfo.getProCooperationInfoId());
            }
        }
        return R.ok();
    }

}
