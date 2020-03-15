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

        return R.ok().put("page", page)
                .put("institute", institute)
                .put("grade", grade);
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
