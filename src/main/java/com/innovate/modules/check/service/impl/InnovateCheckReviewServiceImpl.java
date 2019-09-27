package com.innovate.modules.check.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.check.dao.InnovateCheckReviewDao;
import com.innovate.modules.check.entity.InnovateCheckInfoEntity;
import com.innovate.modules.check.entity.InnovateCheckReviewEntity;
import com.innovate.modules.check.service.InnovateCheckInfoService;
import com.innovate.modules.check.service.InnovateCheckReviewService;
import com.innovate.modules.declare.dao.DeclareReviewDao;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.entity.DeclareReviewEntity;
import com.innovate.modules.declare.entity.DeclareTeacherEntity;
import com.innovate.modules.declare.service.DeclareApplyService;
import com.innovate.modules.declare.service.DeclareInfoService;
import com.innovate.modules.declare.service.DeclareReviewService;
import com.innovate.modules.declare.service.DeclareTeacherService;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;
import com.innovate.modules.innovate.service.InnovateReviewGroupUserService;
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
public class InnovateCheckReviewServiceImpl extends ServiceImpl<InnovateCheckReviewDao, InnovateCheckReviewEntity> implements InnovateCheckReviewService {
    @Autowired
    private InnovateCheckReviewService innovateCheckReviewService;
    @Autowired
    private InnovateCheckInfoService innovateCheckInfoService;
    @Autowired
    private DeclareTeacherService declareTeacherService;
    @Autowired
    private InnovateReviewGroupUserService innovateReviewGroupUserService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<InnovateCheckReviewEntity> queryAll(Map<String, Object> params) {
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
        Long checkId = Long.parseLong(params.get("checkId").toString());
        Long groupId = Long.parseLong(params.get("groupId").toString());
        Long userId = Long.parseLong(params.get("userId").toString());
        String apply = params.get("apply").toString();
        String reApply = params.get("reApply").toString();
//        更新评委组
        InnovateCheckInfoEntity innovateCheckInfoEntity = innovateCheckInfoService.selectById(checkId);
        innovateCheckInfoEntity.setGroupId(groupId);
        //3：已经分配评委组状态
//        innovateCheckInfoEntity.setProjectCheckApplyStatus(3);
        innovateCheckInfoService.updateById(innovateCheckInfoEntity);
//        查询项目全部老师
        List<DeclareTeacherEntity> declareTeacherEntities = declareTeacherService.queryAll(params);
//        查询组内老师
        List<InnovateReviewGroupUserEntity> innovateReviewGroupUserEntities = innovateReviewGroupUserService.queryAllGroupUser(groupId);
//        移除全部评委
        innovateCheckReviewService.remove(params);

        InnovateCheckReviewEntity innovateCheckReviewEntity = null;
        Set<InnovateCheckReviewEntity> tempSet = new HashSet<InnovateCheckReviewEntity>();
        for (int index = 0; index < innovateReviewGroupUserEntities.size(); index++) {
            for (int indexJ = 0; indexJ < declareTeacherEntities.size(); indexJ++) {
                if (innovateReviewGroupUserEntities.get(index).getUserId() != declareTeacherEntities.get(indexJ).getUserId()) {
                    innovateCheckReviewEntity = new InnovateCheckReviewEntity();
                    innovateCheckReviewEntity.setApply(apply);
                    innovateCheckReviewEntity.setCheckId(checkId);
                    innovateCheckReviewEntity.setUserName(sysUserService.selectById(innovateReviewGroupUserEntities.get(index).getUserId()).getName());
                    innovateCheckReviewEntity.setUserId(innovateReviewGroupUserEntities.get(index).getUserId());
                    tempSet.add(innovateCheckReviewEntity);
                }
            }
        }
        for (InnovateCheckReviewEntity tempdeclareReviewEntity: tempSet) {
            innovateCheckReviewService.insert(tempdeclareReviewEntity);
        }
        if (reApply.equals("false")) {
            innovateCheckInfoService.apply(params);
        }
    }

    @Override
    public void score(InnovateCheckReviewEntity declareReviewEntity) {
        Long checkId = declareReviewEntity.getCheckId();
        innovateCheckReviewService.updateById(declareReviewEntity);
        String apply = declareReviewEntity.getApply();
        Map<String, Object> params = new HashMap<>();
        params.put("checkId", checkId);
        params.put("apply", apply);
        params.put("roleId", 6);
        Long count = innovateCheckReviewService.queryCount(params);
        if (count == 0L){
            //更新状态
            innovateCheckInfoService.apply(params);
            //计算平均分
            Double scoreAvg = innovateCheckReviewService.queryScoreAvg(params);
            InnovateCheckInfoEntity innovateCheckInfoEntity = innovateCheckInfoService.selectById(checkId);
            innovateCheckInfoEntity.setCheckScoreAvg(scoreAvg);
            innovateCheckInfoService.updateById(innovateCheckInfoEntity);
        }
    }

    @Override
    public InnovateCheckReviewEntity queryScore(Map<String,Object> params) {
        return baseMapper.queryScore(params);
    }
}
