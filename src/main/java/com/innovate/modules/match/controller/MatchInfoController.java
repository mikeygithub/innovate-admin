package com.innovate.modules.match.controller;

import com.google.gson.Gson;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.common.utils.ShiroUtils;
import com.innovate.modules.innovate.entity.*;
import com.innovate.modules.innovate.service.*;
import com.innovate.modules.match.entity.MatchInfoEntity;
import com.innovate.modules.match.entity.MatchInfoModel;
import com.innovate.modules.match.service.MatchInfoModelService;
import com.innovate.modules.match.service.MatchInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.SecurityUtils;
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
@RequestMapping("innovate/match/info")
public class MatchInfoController extends AbstractController {

    @Autowired
    private MatchInfoService innovatematchInfoService;
    @Autowired
    private InnovateInstituteService innovateInstituteService;
    @Autowired
    private InnovateGradeService innovateGradeService;
    @Autowired
    private UserTeacherInfoService userTeacherInfoService;
    @Autowired
    private MatchInfoModelService matchInfoModelService;

    private MatchInfoEntity tempMatchInfoEntity;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:match:list")
    public R list(@RequestParam Map<String, Object> params){
        /*获得当前用户的所属部门*/
        Long erInstituteId = ShiroUtils.getUserEntity().getInstituteId();
        params.put("erInstituteId",erInstituteId);
        PageUtils page = matchInfoModelService.queryPage(params);

        List<InnovateInstituteEntity> institute = innovateInstituteService.queryAllInstitute();
        List<InnovateGradeEntity> grade = innovateGradeService.queryAllGrade();

        /*查询指导老师*/
        List<UserTeacherInfoEntity> matchTeacherInfoEntities=new CopyOnWriteArrayList<UserTeacherInfoEntity>();

        for (int i = 0; i < page.getList().size() ; i++) {

            MatchInfoModel matchInfoModel = (MatchInfoModel) page.getList().get(i);

            for (UserTeacherInfoEntity userTeacherInfoEntity:userTeacherInfoService.queryMatchTeacherInfo(matchInfoModel.getMatchInfoEntity().getMatchId())){

                if (!matchTeacherInfoEntities.contains(userTeacherInfoEntity)){

                    matchTeacherInfoEntities.add(userTeacherInfoEntity);

                }

            }

        }
        return R.ok()
                .put("page", page)
                .put("institute", institute)
                .put("grade", grade)
                .put("teacher",matchTeacherInfoEntities);

    }

    /**
     * 按照学院查询统计汇总该学院项目信息
     * @param params
     * @return
     */
    @GetMapping("/erCollect")
    @RequiresPermissions("innovate:match:info")
    public R erCollect(@RequestParam Map<String, Object> params){

        Long instituteId = Long.parseLong(params.get("instituteId").toString());

        List<UserTeacherInfoEntity> userTeacherInfoEntities=new LinkedList<>();

        //参赛项目信息
        List<MatchInfoModel> matchInfoList = matchInfoModelService.queryErCollect(params);
        //指导老师信息
        for (MatchInfoModel matchInfoModel:matchInfoList){

            List<UserTeacherInfoEntity> userTeacherInfoEntitie = userTeacherInfoService.queryMatchTeacherInfo(matchInfoModel.getMatchInfoEntity().getMatchId());

            for (UserTeacherInfoEntity userTeacherInfoEntity:userTeacherInfoEntitie){

                if(!userTeacherInfoEntities.contains(userTeacherInfoEntity)){

                    userTeacherInfoEntities.add(userTeacherInfoEntity);

                }
            }
        }

        return R.ok().put("matchInfoList", matchInfoList).put("userTeacherInfoEntities", userTeacherInfoEntities);
    }
    /**
     * 未通过的项目
     */
    @GetMapping("/nopass")
    @RequiresPermissions("innovate:match:list")
    public R noPass(@RequestParam Map<String, Object> params){
        List<MatchInfoEntity> matchInfoEntities = innovatematchInfoService.noPass(params);
        return R.ok()
                .put("matchInfoEntities", matchInfoEntities);
    }

    /**
     * 信息
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:match:info")
    public R info(@RequestParam Map<String, Object> params){
        Long matchId = Long.parseLong(params.get("matchId").toString());
        MatchInfoModel matchInfo = matchInfoModelService.query(params);
        List<UserTeacherInfoEntity> userTeacherInfoEntities = userTeacherInfoService.queryMatchTeacherInfo(matchId);
        return R.ok()
                .put("matchInfo", matchInfo)
                .put("userTeacherInfoEntities", userTeacherInfoEntities);
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:match:save")
    public R save(@RequestBody(required = false) MatchInfoModel matchInfoModel){
        matchInfoModelService.saveEntity(matchInfoModel);
        return R.ok();
    }

    /**
     * 获奖情况
     */
    @PostMapping("/award")
    @RequiresPermissions("innovate:match:info")
    public R award(@RequestBody(required = false) MatchInfoModel matchInfoModel){
        matchInfoModelService.saveAwardEntity(matchInfoModel);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:match:update")
    public R update(@RequestBody(required = false) MatchInfoModel matchInfoModel){
        System.out.println(new Gson().toJson(matchInfoModel.getMatchAwardEntities()));
        matchInfoModelService.updateEntity(matchInfoModel);
        tempMatchInfoEntity = innovatematchInfoService.selectById(matchInfoModel.getMatchInfoEntity().getMatchId());
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:match:delete")
    public R delete(@RequestBody Long[] matchIds){
        Map<String, Object> params = new HashMap<>();
        for (Long matchId: matchIds) {
            params.put("matchId", matchId);
            matchInfoModelService.deleteEntity(params);
        }
        return R.ok();
    }

}
