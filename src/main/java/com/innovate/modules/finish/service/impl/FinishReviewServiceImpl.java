package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.finish.entity.FinishInfoEntity;
import com.innovate.modules.finish.entity.FinishReviewEntity;
import com.innovate.modules.finish.entity.FinishTeacherEntity;
import com.innovate.modules.finish.service.FinishTeacherService;
import com.innovate.modules.finish.dao.FinishReviewDao;
import com.innovate.modules.finish.service.FinishApplyService;
import com.innovate.modules.finish.service.FinishInfoService;
import com.innovate.modules.finish.service.FinishReviewService;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;
import com.innovate.modules.innovate.service.InnovateReviewGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:43
 * @Version 1.0
 */
@Service
public class FinishReviewServiceImpl extends ServiceImpl<FinishReviewDao, FinishReviewEntity> implements FinishReviewService {
    @Autowired
    private FinishReviewService finishReviewService;
    @Autowired
    private FinishApplyService finishApplyService;
    @Autowired
    private FinishInfoService finishInfoService;
    @Autowired
    private FinishTeacherService finishTeacherService;
    @Autowired
    private InnovateReviewGroupUserService innovateReviewGroupUserService;

    @Override
    public List<FinishReviewEntity> queryAll(Map<String, Object> params) {
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
    public void reviewUser(Map<String,Object> params) {
        Long finishId = Long.parseLong(params.get("finishId").toString());
        Long groupId = Long.parseLong(params.get("groupId").toString());
        Long userId = Long.parseLong(params.get("userId").toString());
        String apply = params.get("apply").toString();
        String reApply = params.get("reApply").toString();
        FinishInfoEntity finishInfoEntity = finishInfoService.selectById(finishId);
        finishInfoEntity.setGroupId(groupId);
        finishInfoService.updateById(finishInfoEntity);
        List<FinishTeacherEntity> finishTeacherEntities = finishTeacherService.queryAll(params);
        List<InnovateReviewGroupUserEntity> innovateReviewGroupUserEntities = innovateReviewGroupUserService.queryAllGroupUser(groupId);
        finishReviewService.remove(params);
        FinishReviewEntity finishReviewEntity = null;
        for (int index = 0; index < innovateReviewGroupUserEntities.size(); index++) {
            for (int indexJ = 0; indexJ < finishTeacherEntities.size(); indexJ++) {
                if (innovateReviewGroupUserEntities.get(index).getUserId() != finishTeacherEntities.get(indexJ).getUserId()) {
                    finishReviewEntity = new FinishReviewEntity();
                    finishReviewEntity.setApply(apply);
                    finishReviewEntity.setFinishId(finishId);
                    finishReviewEntity.setUserId(innovateReviewGroupUserEntities.get(index).getUserId());
                    finishReviewService.insert(finishReviewEntity);
                }
            }
        }
        if (reApply.equals("false")) {
            finishApplyService.apply(params);
        }
    }

    @Override
    public void score(FinishReviewEntity finishReviewEntity) {
        Long finishId = finishReviewEntity.getFinishId();
        finishReviewService.updateById(finishReviewEntity);
        String apply = finishReviewEntity.getApply();
        Map<String, Object> params = new HashMap<>();
        params.put("finishId", finishId);
        params.put("apply", apply);
        params.put("roleId", 6);
        Long count = finishReviewService.queryCount(params);
        if (count == 0L){
            finishApplyService.apply(params);
            //计算平均分
            Double scoreAvg = finishReviewService.queryScoreAvg(params);
            FinishInfoEntity finishInfoEntity = finishInfoService.selectById(finishId);
            finishInfoEntity.setFinishScoreAvg(scoreAvg);
            finishInfoService.updateById(finishInfoEntity);
        }
    }

    @Override
    public FinishReviewEntity queryScore(Map<String,Object> params) {
        return baseMapper.queryScore(params);
    }
}
