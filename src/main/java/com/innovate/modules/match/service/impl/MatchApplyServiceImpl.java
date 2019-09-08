package com.innovate.modules.match.service.impl;

import com.innovate.modules.innovate.config.ConfigApi;
import com.innovate.modules.match.entity.MatchInfoEntity;
import com.innovate.modules.match.service.MatchApplyService;
import com.innovate.modules.match.service.MatchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/24
 **/
@Service
public class MatchApplyServiceImpl implements MatchApplyService {

    @Autowired
    private MatchInfoService matchInfoService;

    @Override
    public void apply(Map<String, Object> params) {
        Long matchId = Long.parseLong(params.get("matchId").toString());
        Long roleId = Long.parseLong(params.get("roleId").toString());
        Long applyStatus = null;
        String apply = params.get("apply").toString();
        MatchInfoEntity matchInfoEntity = matchInfoService.selectById(matchId);

        switch (apply) {
            case "project_match_apply_status": {
//                项目中的流程值
                applyStatus = matchInfoEntity.getProjectMatchApplyStatus();
//                判断当前项目审批流程保存的值是否等于角色id，相等便通过
                if (roleId == ConfigApi.matchListName[applyStatus.intValue()]) {
                    matchInfoEntity.setProjectMatchApplyStatus(++applyStatus);
                }
                break;
            }
        }
        matchInfoService.updateById(matchInfoEntity);
    }
}
