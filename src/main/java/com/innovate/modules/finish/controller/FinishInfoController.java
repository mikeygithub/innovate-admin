package com.innovate.modules.finish.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.common.utils.ShiroUtils;
import com.innovate.modules.finish.entity.FinishInfoEntity;
import com.innovate.modules.finish.entity.FinishInfoModel;
import com.innovate.modules.finish.service.FinishInfoModelService;
import com.innovate.modules.finish.service.FinishInfoService;
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
@RequestMapping("innovate/finish/info")
public class FinishInfoController extends AbstractController {

    @Autowired
    private FinishInfoService innovatefinishInfoService;
    @Autowired
    private InnovateInstituteService innovateInstituteService;
    @Autowired
    private InnovateGradeService innovateGradeService;
    @Autowired
    private UserTeacherInfoService userTeacherInfoService;
    @Autowired
    private FinishInfoModelService finishInfoModelService;

    private FinishInfoEntity tempFinishInfoEntity;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:finish:list")
    public R list(@RequestParam Map<String, Object> params){
        /*获得当前用户的所属部门*/
        Long erInstituteId = ShiroUtils.getUserEntity().getInstituteId();
        params.put("erInstituteId",erInstituteId);
        PageUtils page = finishInfoModelService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 未通过的项目
     */
    @GetMapping("/nopass")
    @RequiresPermissions("innovate:finish:list")
    public R noPass(@RequestParam Map<String, Object> params){
        List<FinishInfoEntity> finishInfoEntities = innovatefinishInfoService.noPass(params);
        return R.ok()
                .put("finishInfoEntities", finishInfoEntities);
    }

    /**
     * 信息
     */
    @GetMapping("/info")
    @RequiresPermissions("innovate:finish:info")
    public R info(@RequestParam Map<String, Object> params){
        Long finishId = Long.parseLong(params.get("finishId").toString());
        FinishInfoModel finishInfo = finishInfoModelService.query(params);
        List<UserTeacherInfoEntity> userTeacherInfoEntities = userTeacherInfoService.queryFinishTeacherInfo(finishId);
        return R.ok()
                .put("finishInfo", finishInfo)
                .put("userTeacherInfoEntities", userTeacherInfoEntities);
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:finish:save")
    public R save(@RequestBody(required = false) FinishInfoModel finishInfoModel){
        finishInfoModelService.saveEntity(finishInfoModel);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:finish:update")
    public R update(@RequestBody(required = false) FinishInfoModel finishInfoModel){
        finishInfoModelService.updateEntity(finishInfoModel);
        tempFinishInfoEntity = innovatefinishInfoService.selectById(finishInfoModel.getFinishInfoEntity().getFinishId());
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:finish:delete")
    public R delete(@RequestBody Long[] finishIds){
        Map<String, Object> params = new HashMap<>();
        for (Long finishId: finishIds) {
            params.put("finishId", finishId);
            finishInfoModelService.deleteEntity(params);
        }
        return R.ok();
    }

}
