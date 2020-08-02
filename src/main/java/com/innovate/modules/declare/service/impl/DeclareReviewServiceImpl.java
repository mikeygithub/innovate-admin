package com.innovate.modules.declare.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.declare.dao.DeclareReviewDao;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.entity.DeclareReviewEntity;
import com.innovate.modules.declare.entity.DeclareTeacherEntity;
import com.innovate.modules.declare.service.DeclareApplyService;
import com.innovate.modules.declare.service.DeclareInfoService;
import com.innovate.modules.declare.service.DeclareReviewService;
import com.innovate.modules.declare.service.DeclareTeacherService;
import com.innovate.modules.innovate.entity.InnovateGradeEntity;
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
public class DeclareReviewServiceImpl extends ServiceImpl<DeclareReviewDao, DeclareReviewEntity> implements DeclareReviewService {
    @Autowired
    private DeclareReviewService declareReviewService;
    @Autowired
    private DeclareApplyService declareApplyService;
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private DeclareTeacherService declareTeacherService;
    @Autowired
    private InnovateReviewGroupUserService innovateReviewGroupUserService;

    @Autowired
    private SysUserService sysUserService;



    @Override
    public List<DeclareReviewEntity> queryAll(Map<String, Object> params) {
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

    /**
     * 查询未评分的项目
     * @param params
     * @return
     */
    @Override
    public PageUtils unReview(Map<String, Object> params) {

        EntityWrapper<DeclareReviewEntity> declareReviewEntityEntityWrapper = new EntityWrapper<>();

        declareReviewEntityEntityWrapper.isNull("score").eq("is_del",0);//未评分

        if (params.get("declareTime")!=null)declareReviewEntityEntityWrapper.like("review_year",params.get("declareTime").toString());

        Page<DeclareReviewEntity> page = this.selectPage(
                    new Query<DeclareReviewEntity>(params).getPage(),declareReviewEntityEntityWrapper
                    );

        List<DeclareReviewEntity> records = page.getRecords();

        records.forEach(v->{
            v.setSysUserEntity(sysUserService.selectById(v.getUserId()));
            v.setDeclareInfoEntity(declareInfoService.selectById(v.getDeclareId()));
        });

        page.setRecords(records);

        return new PageUtils(page);
    }

    @Override
    public void reviewUser(Map<String,Object> params) {
        Long declareId = Long.parseLong(params.get("declareId").toString());
        Long groupId = Long.parseLong(params.get("groupId").toString());
        Long userId = Long.parseLong(params.get("userId").toString());
        String apply = params.get("apply").toString();
        String reApply = params.get("reApply").toString();
//        更新评委组
        DeclareInfoEntity declareInfoEntity = declareInfoService.selectById(declareId);
        declareInfoEntity.setGroupId(groupId);
        declareInfoService.updateById(declareInfoEntity);
//        查询项目全部老师
        List<DeclareTeacherEntity> declareTeacherEntities = declareTeacherService.queryAll(params);
//        查询组内老师
        List<InnovateReviewGroupUserEntity> innovateReviewGroupUserEntities = innovateReviewGroupUserService.queryAllGroupUser(groupId);
//        移除全部评委
        declareReviewService.remove(params);

        DeclareReviewEntity declareReviewEntity = null;
        Set<DeclareReviewEntity> tempSet = new HashSet<DeclareReviewEntity>();
        for (int index = 0; index < innovateReviewGroupUserEntities.size(); index++) {
            for (int indexJ = 0; indexJ < declareTeacherEntities.size(); indexJ++) {
                if (innovateReviewGroupUserEntities.get(index).getUserId() != declareTeacherEntities.get(indexJ).getUserId()) {
                    declareReviewEntity = new DeclareReviewEntity();
                    declareReviewEntity.setApply(apply);
                    declareReviewEntity.setReviewYear(declareInfoEntity.getDeclareYear());//年度
                    declareReviewEntity.setDeclareId(declareId);
                    declareReviewEntity.setUserId(innovateReviewGroupUserEntities.get(index).getUserId());
                    tempSet.add(declareReviewEntity);
                }
            }
        }
        for (DeclareReviewEntity tempdeclareReviewEntity: tempSet) {
            declareReviewService.insert(tempdeclareReviewEntity);
        }
        if (reApply.equals("false")) {
            declareApplyService.apply(params);
        }
    }

    @Override
    public void score(DeclareReviewEntity declareReviewEntity) {
        Long declareId = declareReviewEntity.getDeclareId();
        declareReviewService.updateById(declareReviewEntity);
        String apply = declareReviewEntity.getApply();
        Map<String, Object> params = new HashMap<>();
        params.put("declareId", declareId);
        params.put("apply", apply);
        params.put("roleId", 6);
        Long count = declareReviewService.queryCount(params);
        if (count == 0L){
            declareApplyService.apply(params);
            //计算平均分
            Double scoreAvg = declareReviewService.queryScoreAvg(params);
            DeclareInfoEntity declareInfoEntity = declareInfoService.selectById(declareId);
            declareInfoEntity.setDeclareScoreAvg(scoreAvg);
            declareInfoService.updateById(declareInfoEntity);
        }
    }

    @Override
    public DeclareReviewEntity queryScore(Map<String,Object> params) {
        return baseMapper.queryScore(params);
    }
}
