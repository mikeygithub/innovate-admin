package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.match.dao.MatchRetreatDao;
import com.innovate.modules.match.entity.MatchInfoEntity;
import com.innovate.modules.match.entity.MatchRetreatEntity;
import com.innovate.modules.match.service.MatchInfoService;
import com.innovate.modules.match.service.MatchRetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
@Service
public class MatchRetreatServiceImpl extends ServiceImpl<MatchRetreatDao, MatchRetreatEntity> implements MatchRetreatService {

    @Autowired
    private MatchRetreatService matchRetreatService;
    @Autowired
    private MatchInfoService innovateMatchInfoService;

    @Override
    public void updateRetreat(Map<String, Object> params) {
        Long matchId = Long.parseLong(params.get("matchId").toString());
        String apply = params.get("apply").toString();
        Long userId = Long.parseLong(params.get("userId").toString());
        String option = params.get("option").toString();
        //项目中的流程值
        Long applyStatus = Long.parseLong(params.get("applyStatus").toString());
        MatchRetreatEntity matchRetreatEntity = new MatchRetreatEntity();
        matchRetreatEntity.setMatchId(matchId);
        matchRetreatEntity.setApply(apply);
        matchRetreatEntity.setRetreatOption(option);
        matchRetreatEntity.setUserId(userId);
        matchRetreatEntity.setApplyStatus(applyStatus);
        matchRetreatService.insert(matchRetreatEntity);
        //
        MatchInfoEntity matchInfo = innovateMatchInfoService.selectById(matchId);

        switch (apply) {
            case "project_match_apply_status":
                matchInfo.setMatchNoPass(1L);
                break;
        }
        innovateMatchInfoService.updateAllColumnById(matchInfo);

    }

    @Override
    public List<MatchRetreatEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }

}
