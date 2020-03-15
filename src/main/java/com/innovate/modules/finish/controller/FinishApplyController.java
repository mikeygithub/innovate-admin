package com.innovate.modules.finish.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.finish.service.FinishApplyService;
import com.innovate.modules.match.entity.MatchInfoEntity;
import com.innovate.modules.match.service.MatchInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title: 审核
 * @Description:
 * @date 2018/11/8 21:10
 * @Version 1.0
 */
@RestController
@RequestMapping("innovate/finish/apply")
public class FinishApplyController extends AbstractController {
    @Autowired
    private FinishApplyService finishApplyService;
    @Autowired
    private MatchInfoService matchInfoService;

    /**
     * 审核（状态修改）
     * @param
     * @return
     */
    @PostMapping("/apply")
    @RequiresPermissions("innovate:finish:info")
    public R apply(@RequestParam Map<String, Object> params){
        List<MatchInfoEntity> matchInfoEntityList = matchInfoService.queryByName(params);
        if (matchInfoEntityList != null){
            finishApplyService.apply(params);
            return R.ok();
        }else {
            return R.error("该项目未参与比赛，不予结题");
        }
    }

}
