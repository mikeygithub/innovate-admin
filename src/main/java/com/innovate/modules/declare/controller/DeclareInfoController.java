package com.innovate.modules.declare.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.common.utils.ShiroUtils;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.entity.DeclareInfoModel;
import com.innovate.modules.declare.service.DeclareInfoModelService;
import com.innovate.modules.declare.service.DeclareInfoService;
import com.innovate.modules.innovate.entity.InnovateGradeEntity;
import com.innovate.modules.innovate.entity.InnovateInstituteEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.InnovateGradeService;
import com.innovate.modules.innovate.service.InnovateInstituteService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 21:10
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/declare/info")
public class DeclareInfoController extends AbstractController {

    @Autowired
    private DeclareInfoService innovatedeclareInfoService;
    @Autowired
    private InnovateInstituteService innovateInstituteService;
    @Autowired
    private InnovateGradeService innovateGradeService;
    @Autowired
    private UserTeacherInfoService userTeacherInfoService;
    @Autowired
    private DeclareInfoModelService declareInfoModelService;

    private DeclareInfoEntity tempDeclareInfoEntity;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:declare:list")
    public R list(@RequestParam Map<String, Object> params){
        /*获得当前用户的所属部门*/
        Long erInstituteId = ShiroUtils.getUserEntity().getInstituteId();
        params.put("erInstituteId",erInstituteId);
        PageUtils page = declareInfoModelService.queryPage(params);
        //学院
        List<InnovateInstituteEntity> institute = innovateInstituteService.queryAllInstitute();
        //年级
        List<InnovateGradeEntity> grade = innovateGradeService.queryAllGrade();

        return R.ok()
                .put("page", page)
                .put("institute", institute)
                .put("grade", grade);
    }
    /**
     * 可以结题的项目
     */
    @GetMapping("/canfinish")
    @RequiresPermissions("innovate:declare:list")
    public R canFinish(@RequestParam Map<String, Object> params){

        List<DeclareInfoEntity> declareInfoEntities = declareInfoModelService.queryCanFinish(params);
        //学院
        return R.ok().put("declareInfoEntities", declareInfoEntities);
    }
    /**
     * 未通过的项目
     */
    @GetMapping("/nopass")
    @RequiresPermissions("innovate:declare:list")
    public R noPass(@RequestParam Map<String, Object> params){
        List<DeclareInfoEntity> declareInfoEntities = innovatedeclareInfoService.noPass(params);
        return R.ok()
                .put("declareInfoEntities", declareInfoEntities);
    }



    /**
     * 等级排序
     */
    @PostMapping("/order")
    @RequiresPermissions("innovate:declare:order")
    public R order(@RequestBody(required = false) DeclareInfoModel declareInfoModel){
        declareInfoModelService.updateEntity(declareInfoModel);
        return R.ok();
    }

    /**
     * 信息
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:declare:info")
    public R info(@RequestParam Map<String, Object> params){
        Long declareId = Long.parseLong(params.get("declareId").toString());
        DeclareInfoModel declareInfo = declareInfoModelService.query(params);
        List<UserTeacherInfoEntity> userTeacherInfoEntities = userTeacherInfoService.queryDeclareTeacherInfo(declareId);
        return R.ok()
                .put("declareInfo", declareInfo)
                .put("userTeacherInfoEntities", userTeacherInfoEntities);
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:declare:save")
    public R save(@RequestBody(required = false) DeclareInfoModel declareInfoModel){
        declareInfoModelService.saveEntity(declareInfoModel);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:declare:update")
    public R update(@RequestBody(required = false) DeclareInfoModel declareInfoModel){
        declareInfoModelService.updateEntity(declareInfoModel);
        tempDeclareInfoEntity = innovatedeclareInfoService.selectById(declareInfoModel.getDeclareInfoEntity().getDeclareId());
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:declare:delete")
    public R delete(@RequestBody Long[] declareIds){
        Map<String, Object> params = new HashMap<>();
        for (Long declareId: declareIds) {
            params.put("declareId", declareId);
            declareInfoModelService.deleteEntity(params);
        }
        return R.ok();
    }

}
