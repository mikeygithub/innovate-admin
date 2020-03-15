package com.innovate.modules.innovate.controller;

import com.google.gson.Gson;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.common.utils.ShiroUtils;
import com.innovate.modules.innovate.entity.*;
import com.innovate.modules.innovate.service.*;
import com.innovate.modules.innovate.utils.ProjectCalculateUtil;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 21:10
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/project/info")
public class ProjectInfoController extends AbstractController {

    @Autowired
    private ProjectInfoService innovateProjectInfoService;
    @Autowired
    private UserTeacherInfoService userTeacherInfoService;
    @Autowired
    private ProjectInfoModelService projectInfoModelService;
    @Autowired
    private BaseProjectStationService baseProjectStationService;

    private ProjectInfoEntity tempProjectInfoEntity;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:project:list")
    public R list(@RequestParam Map<String, Object> params){
        /*获得当前用户的所属部门*/
        Long erInstituteId = ShiroUtils.getUserEntity().getInstituteId();
        params.put("erInstituteId",erInstituteId);
        PageUtils page = projectInfoModelService.queryPage(params);

        return R.ok()
                .put("page", page);
    }

    /**
     * 未通过的项目
     */
    @GetMapping("/nopass")
    @RequiresPermissions("innovate:project:list")
    public R noPass(@RequestParam Map<String, Object> params){
        List<ProjectInfoEntity> projectInfoEntities = innovateProjectInfoService.noPass(params);
        return R.ok()
                .put("projectInfoEntities", projectInfoEntities);
    }

    /**
     * 查所有的项目
     */
    @GetMapping("/all")
    @RequiresPermissions("innovate:project:list")
    public R all(@RequestParam Map<String, Object> params){
        List<ProjectInfoEntity> projectInfoEntities = innovateProjectInfoService.queryAll(params);
        return R.ok()
                .put("projectInfoEntities", projectInfoEntities);
    }

    /**
     * 信息
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:project:info")
    public R info(@RequestParam Map<String, Object> params){
        Long projectId = Long.parseLong(params.get("projectId").toString());
        ProjectInfoModel projectInfo = projectInfoModelService.query(params);
        List<UserTeacherInfoEntity> userTeacherInfoEntities = userTeacherInfoService.queryProjectTeacherInfo(projectId);
        List<BaseProjectStationEntity> baseProjectStationEntities = baseProjectStationService.queryAll();
        return R.ok()
                .put("projectInfo", projectInfo)
                .put("userTeacherInfoEntities", userTeacherInfoEntities)
                .put("baseProjectStationEntities", baseProjectStationEntities);
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:project:save")
    public R save(@RequestBody(required = false) ProjectInfoModel projectInfoModel){
        projectInfoModelService.saveEntity(projectInfoModel);
        Long stationId = projectInfoModel.getProjectInfoEntity().getStationId();
        if (null != stationId) {
            ProjectCalculateUtil.setCalculateId(projectInfoModel.getProjectInfoEntity().getProjectId());
            ProjectCalculateUtil.calculate();
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:project:update")
    public R update(@RequestBody(required = false) ProjectInfoModel projectInfoModel){
        projectInfoModelService.updateEntity(projectInfoModel);
        tempProjectInfoEntity = innovateProjectInfoService.selectById(projectInfoModel.getProjectInfoEntity().getProjectId());
//        Long stationId = projectInfoModel.getProjectInfoEntity().getStationId();
//        if (null != stationId) {
//            ProjectCalculateUtil.setCalculateId(projectInfoModel.getProjectInfoEntity().getProjectId());
//            ProjectCalculateUtil.calculate();
//        }
//        Long stationId2 = tempProjectInfoEntity.getStationId();
//        if (null != stationId2) {
//            ProjectCalculateUtil.setCalculateId(tempProjectInfoEntity.getProjectId());
//            ProjectCalculateUtil.calculate();
//        }
        return R.ok();
    }

    /**
     * 分配工位号
     */
    @PostMapping("/station")
    @RequiresPermissions("innovate:project:list")
    public R station(@RequestBody(required = false) ProjectInfoModel projectInfoModel){
        innovateProjectInfoService.station(projectInfoModel);
        return R.ok();
    }

    /**
     *更改企业状态
     */
    @PostMapping("/status")
    @RequiresPermissions("innovate:project:update")
    public R status(@RequestBody(required = false) ProjectInfoModel projectInfoModel){
        innovateProjectInfoService.status(projectInfoModel);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:project:delete")
    public R delete(@RequestBody Long[] projectIds){
        Map<String, Object> params = new HashMap<>();
        for (Long projectId: projectIds) {
//            tempProjectInfoEntity = innovateProjectInfoService.selectById(projectId);
//            Long stationId = tempProjectInfoEntity.getStationId();
//            if (null != stationId) {
//                ProjectCalculateUtil.setCalculateId(tempProjectInfoEntity.getProjectId());
//                ProjectCalculateUtil.calculate();
//            }
            params.put("projectId", projectId);
            projectInfoModelService.deleteEntity(params);
        }
        return R.ok();
    }

}
