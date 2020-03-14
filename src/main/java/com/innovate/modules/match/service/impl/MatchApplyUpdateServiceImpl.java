package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.match.dao.MatchApplyUpdateDao;
import com.innovate.modules.match.entity.MatchApplyUpdateEntity;
import com.innovate.modules.match.entity.MatchInfoEntity;
import com.innovate.modules.match.service.MatchApplyUpdateService;
import com.innovate.modules.match.service.MatchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目申请修改
 **/
@Service
public class MatchApplyUpdateServiceImpl extends ServiceImpl<MatchApplyUpdateDao, MatchApplyUpdateEntity> implements MatchApplyUpdateService {

    @Autowired
    private MatchApplyUpdateService matchApplyUpdateService;
    @Autowired
    private MatchInfoService matchInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long matchId = Long.parseLong(params.get("matchId").toString());
        Page<MatchApplyUpdateEntity> page = this.selectPage(
                new Query<MatchApplyUpdateEntity>(params).getPage(),
                new EntityWrapper<MatchApplyUpdateEntity>()
                        .eq(matchId != null, "match_id", matchId)
        );
        return new PageUtils(page);
    }

    @Override
    public void update(MatchApplyUpdateEntity matchApplyUpdateEntity) {
        Long matchId = matchApplyUpdateEntity.getMatchId();
        Long result = matchApplyUpdateEntity.getResult();
        MatchInfoEntity matchInfoEntity = matchInfoService.selectById(matchId);
        if (1 == result) {//0不通过 1通过
            matchInfoEntity.setIsUpdate(1L);
            matchInfoEntity.setApplyUpdate(0L);
        }
        if (2 == result || 0 == result) {//0不通过 1通过
            matchInfoEntity.setApplyUpdate(0L);
        }
        if (null == result) {//0不通过 1通过
            matchInfoEntity.setApplyUpdate(1L);
        }
        matchInfoService.updateAllColumnById(matchInfoEntity);
        matchApplyUpdateService.updateAllColumnById(matchApplyUpdateEntity);
    }

    @Override
    public void applyUpdate(Map<String, Object> params) {
        Long matchId = Long.parseLong(params.get("matchId").toString());
        String reason = params.get("reason").toString();
        MatchInfoEntity matchInfoEntity = matchInfoService.selectById(matchId);
        matchInfoEntity.setApplyUpdate(1L);
        matchInfoService.updateAllColumnById(matchInfoEntity);
        MatchApplyUpdateEntity matchApplyUpdateEntity = new MatchApplyUpdateEntity();
        matchApplyUpdateEntity.setMatchId(matchId);
        matchApplyUpdateEntity.setReason(reason);
        matchApplyUpdateService.insert(matchApplyUpdateEntity);
    }

    @Override
    public List<MatchApplyUpdateEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        List<MatchApplyUpdateEntity> matchApplyUpdateEntityList = baseMapper.queryAll(params);
        for (MatchApplyUpdateEntity matchApplyUpdateEntity : matchApplyUpdateEntityList) {
            baseMapper.remove(params);
        }
    }
}
