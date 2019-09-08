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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

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

        List<InnovateInstituteEntity> institute = innovateInstituteService.queryAllInstitute();
        List<InnovateGradeEntity> grade = innovateGradeService.queryAllGrade();

        /*查询指导老师*/
        List<UserTeacherInfoEntity> declareTeacherInfoEntities=new CopyOnWriteArrayList<UserTeacherInfoEntity>();

        for (int i = 0; i < page.getList().size() ; i++) {

            DeclareInfoModel declareInfoModel = (DeclareInfoModel) page.getList().get(i);

            for (UserTeacherInfoEntity userTeacherInfoEntity:userTeacherInfoService.queryDeclareTeacherInfo(declareInfoModel.getDeclareInfoEntity().getDeclareId())){

                if (!declareTeacherInfoEntities.contains(userTeacherInfoEntity)){

                    declareTeacherInfoEntities.add(userTeacherInfoEntity);

                }

            }

        }
        return R.ok()
                .put("page", page)
                .put("institute", institute)
                .put("grade", grade)
                .put("teacher",declareTeacherInfoEntities);
    }
    /**
     * 按照学院查询统计汇总该学院项目信息
     * @param params
     * @return
     */
    @GetMapping("/erCollect")
    @RequiresPermissions("innovate:declare:info")
    public R erCollect(@RequestParam Map<String, Object> params){

        Long instituteId = Long.parseLong(params.get("instituteId").toString());

        List<UserTeacherInfoEntity> userTeacherInfoEntities=new LinkedList<>();

        //大创项目信息
        List<DeclareInfoModel> declareInfoModels = declareInfoModelService.queryErCollect(params);
        //指导老师信息
        for (DeclareInfoModel declareInfoModel:declareInfoModels){

            List<UserTeacherInfoEntity> userTeacherInfoEntitie = userTeacherInfoService.queryDeclareTeacherInfo(declareInfoModel.getDeclareInfoEntity().getDeclareId());

            for (UserTeacherInfoEntity userTeacherInfoEntity:userTeacherInfoEntitie){

                if(!userTeacherInfoEntities.contains(userTeacherInfoEntity)){

                    userTeacherInfoEntities.add(userTeacherInfoEntity);

                }
            }
        }

        return R.ok().put("declareInfoList", declareInfoModels).put("userTeacherInfoEntities", userTeacherInfoEntities);
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
