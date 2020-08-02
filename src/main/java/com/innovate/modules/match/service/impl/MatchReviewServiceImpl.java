package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.declare.entity.DeclareReviewEntity;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;
import com.innovate.modules.innovate.service.InnovateReviewGroupUserService;
import com.innovate.modules.match.dao.MatchReviewDao;
import com.innovate.modules.match.entity.MatchInfoEntity;
import com.innovate.modules.match.entity.MatchReviewEntity;
import com.innovate.modules.match.entity.MatchTeacherEntity;
import com.innovate.modules.match.service.*;
import com.innovate.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:43
 * @Version 1.0
 */
@Service
public class MatchReviewServiceImpl extends ServiceImpl<MatchReviewDao, MatchReviewEntity> implements MatchReviewService {
    @Autowired
    private MatchReviewService matchReviewService;
    @Autowired
    private MatchApplyService matchApplyService;
    @Autowired
    private MatchInfoService matchInfoService;
    @Autowired
    private MatchTeacherService matchTeacherService;
    @Autowired
    private InnovateReviewGroupUserService innovateReviewGroupUserService;
    @Autowired
    private SysUserService sysUserService;


    @Override
    public List<MatchReviewEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }

    @Override
    public Long queryCount(Map<String, Object> params){
        return baseMapper.queryCount(params);
    }

    @Override
    public Double queryScoreAvg(Map<String, Object> params) {
        return baseMapper.queryScoreAvg(params);
    }

    @Override
    public PageUtils unReview(Map<String, Object> params) {


        EntityWrapper<MatchReviewEntity> ew = new EntityWrapper<>();

        ew.isNull("score").eq("is_del",0);//未评分

        if (params.get("matchTime")!=null)ew.like("match_year",params.get("matchTime").toString());

        Page<MatchReviewEntity> page = this.selectPage(
                new Query<MatchReviewEntity>(params).getPage(),ew
        );

        List<MatchReviewEntity> records = page.getRecords();

        records.forEach(v->{
            v.setSysUserEntity(sysUserService.selectById(v.getUserId()));
            v.setMatchInfoEntity(matchInfoService.selectById(v.getMatchId()));
        });

        page.setRecords(records);

        return new PageUtils(page);

    }

    @Override
    public void reviewUser(Map<String,Object> params) {
        Long matchId = Long.parseLong(params.get("matchId").toString());
        Long groupId = Long.parseLong(params.get("groupId").toString());
        Long userId = Long.parseLong(params.get("userId").toString());
        String apply = params.get("apply").toString();
        String reApply = params.get("reApply").toString();
        MatchInfoEntity matchInfoEntity = matchInfoService.selectById(matchId);
        matchInfoEntity.setGroupId(groupId);
        matchInfoService.updateById(matchInfoEntity);

        //查询老师
        List<MatchTeacherEntity> matchTeacherEntities = matchTeacherService.queryAll(params);
        //评委组
        List<InnovateReviewGroupUserEntity> innovateReviewGroupUserEntities = innovateReviewGroupUserService.queryAllGroupUser(groupId);
        //移除全部评委
        matchReviewService.remove(params);
        Set<MatchReviewEntity> tempSet = new HashSet<>();
        MatchReviewEntity matchReviewEntity = null;
        for (int index = 0; index < innovateReviewGroupUserEntities.size(); index++) {
            for (int indexJ = 0; indexJ < matchTeacherEntities.size(); indexJ++) {
                if (innovateReviewGroupUserEntities.get(index).getUserId() != matchTeacherEntities.get(indexJ).getUserId()) {
                    matchReviewEntity = new MatchReviewEntity();
                    matchReviewEntity.setApply(apply);
                    matchReviewEntity.setMatchId(matchId);
                    matchReviewEntity.setMatchYear(Long.parseLong(matchInfoEntity.getMatchTime().toString().substring(0,3)));
                    matchReviewEntity.setUserId(innovateReviewGroupUserEntities.get(index).getUserId());
                    tempSet.add(matchReviewEntity);
                }
            }
        }
        matchReviewService.insertBatch(new ArrayList<>(tempSet));
        if (reApply.equals("false")) {
            matchApplyService.apply(params);
        }
    }

    @Override
    public void score(MatchReviewEntity matchReviewEntity) {
        Long matchId = matchReviewEntity.getMatchId();
        matchReviewService.updateById(matchReviewEntity);
        String apply = matchReviewEntity.getApply();
        Map<String, Object> params = new HashMap<>();
        params.put("apply", apply);
        params.put("matchId", matchId);
        params.put("roleId", 6);
        Long count = matchReviewService.queryCount(params);
        if (count == 0L){
            matchApplyService.apply(params);
            //计算平均分
            Double scoreAvg = matchReviewService.queryScoreAvg(params);
            MatchInfoEntity matchInfoEntity = matchInfoService.selectById(matchId);
            matchInfoEntity.setMatchScoreAvg(scoreAvg);
            matchInfoService.updateById(matchInfoEntity);
        }
    }

    @Override
    public MatchReviewEntity queryScore(Map<String,Object> params) {
        return baseMapper.queryScore(params);
    }
}
