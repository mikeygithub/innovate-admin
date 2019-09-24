package com.innovate.modules.check.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.check.dao.InnovateCheckInfoDao;
import com.innovate.modules.check.entity.*;
import com.innovate.modules.check.service.*;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.entity.DeclareInfoModel;
import com.innovate.modules.declare.service.DeclareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;


@Service("innovateCheckInfoService")
public class InnovateCheckInfoServiceImpl extends ServiceImpl<InnovateCheckInfoDao, InnovateCheckInfoEntity> implements InnovateCheckInfoService {
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private InnovateCheckAttachService innovateCheckAttachService;
    @Autowired
    private InnovateCheckRetreatService innovateCheckRetreatService;
    @Autowired
    private InnovateCheckAwardService innovateCheckAwardService;
    @Autowired
    private InnovateCheckReviewService innovateCheckReviewService;


    private List<InnovateCheckInfoModel> innovateCheckInfoModels;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Integer totalPage = baseMapper.queryCountPage(params);

        Integer currPage = 1;
        Integer pageSize = 10;
        try {
            if (params.get("currPage") != null && params.get("pageSize") != null) {
                currPage = Integer.parseInt(params.get("currPage").toString());
                pageSize = Integer.parseInt(params.get("pageSize").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer startPage = 0 + pageSize * (currPage - 1);
        Integer endPage = pageSize;

        params.put("startPage", startPage);
        params.put("endPage", endPage);

        List<InnovateCheckInfoEntity> innovateCheckInfoEntities = baseMapper.queryPage(params);
        innovateCheckInfoModels = new LinkedList<>();
        InnovateCheckInfoModel temp = null;
        for (InnovateCheckInfoEntity info : innovateCheckInfoEntities) {
            //值对象
            temp = new InnovateCheckInfoModel();
            temp.setInnovateCheckInfoEntity(info);
            //对应大创
            DeclareInfoEntity declareInfoEntity = declareInfoService.queryById(info.getDeclareId());
            temp.setDeclareInfoEntity(declareInfoEntity);
            //评委评分
            Map<String,Object> map= new HashMap<>();
            map.put("checkId", info.getCheckId());
            List<InnovateCheckReviewEntity> innovateCheckReviewEntities = innovateCheckReviewService.queryAll(map);
            temp.setInnovateCheckReviewEntities(innovateCheckReviewEntities);
            //附件
            List<InnovateCheckAttachEntity> innovateCheckAttachEntities = innovateCheckAttachService.queryByCheckId(info.getCheckId());
            temp.setInnovateCheckAttachEntities(innovateCheckAttachEntities);
            //奖项
            List<InnovateCheckAwardEntity> innovateCheckAwardEntities = innovateCheckAwardService.queryAll(map);
            temp.setInnovateCheckAwardEntities(innovateCheckAwardEntities);
            //回退记录

            innovateCheckInfoModels.add(temp);
        }

        return new PageUtils(innovateCheckInfoModels, totalPage, pageSize, currPage);
    }

    /**
     * 通过id批量设置需要中期检查的项目
     *
     * @param checkIds
     */
    @Override
    public void saveByDeclareBatchIds(List<Long> checkIds) {
        for (Long id : checkIds) {
            InnovateCheckInfoEntity innovateCheckInfoEntity = new InnovateCheckInfoEntity();
            innovateCheckInfoEntity.setInstituteId(declareInfoService.queryById(id).getInstituteId());
            innovateCheckInfoEntity.setDeclareId(id);
            this.insert(innovateCheckInfoEntity);
        }
    }

    /**
     * 通过年度批量设置需要中期检查的项目
     * @param params
     */
    @Override
    public void saveByTime(Map<String,Object> params) {

        String checkTime = params.get("checkTime").toString();

        EntityWrapper ew = new EntityWrapper<>();
        ew.setEntity(new DeclareInfoEntity());
        //已经评分过的大创列表
        ew.like("declare_time", checkTime, SqlLike.DEFAULT);
        ew.eq("is_del",0);
        ew.eq("audit_no_pass",0);
        ew.isNotNull("declare_score_avg");
        List<DeclareInfoEntity> declareInfoEntities = declareInfoService.selectList(ew);

        for (DeclareInfoEntity declareInfoEntity : declareInfoEntities) {
            InnovateCheckInfoEntity innovateCheckInfoEntity = new InnovateCheckInfoEntity();
            innovateCheckInfoEntity.setInstituteId(declareInfoEntity.getInstituteId());
            innovateCheckInfoEntity.setDeclareId(declareInfoEntity.getDeclareId());
            this.insert(innovateCheckInfoEntity);
        }
    }

    /**
     * 审批
     *
     * @param params
     */
    @Override
    public void apply(Map<String, Object> params) {
        String checkId = params.get("checkId").toString();
        InnovateCheckInfoEntity innovateCheckInfoEntity = this.baseMapper.selectById(checkId);
        Integer projectCheckApplyStatus = innovateCheckInfoEntity.getProjectCheckApplyStatus();
        innovateCheckInfoEntity.setProjectCheckApplyStatus(++projectCheckApplyStatus);
        this.updateById(innovateCheckInfoEntity);
    }

    @Override
    public void save(InnovateCheckInfoModel innovateCheckInfoModel) {
        innovateCheckInfoModel.getInnovateCheckInfoEntity().setCheckNoPass(0);
        innovateCheckAttachService.insertOrUpdateBatch(innovateCheckInfoModel.getInnovateCheckAttachEntities());
        if (innovateCheckInfoModel.getInnovateCheckAttachEntities() != null)
            insertOrUpdate(innovateCheckInfoModel.getInnovateCheckInfoEntity());
        if (innovateCheckInfoModel.getInnovateCheckRetreatEntities() != null)
            innovateCheckRetreatService.insertOrUpdateBatch(innovateCheckInfoModel.getInnovateCheckRetreatEntities());

    }

}