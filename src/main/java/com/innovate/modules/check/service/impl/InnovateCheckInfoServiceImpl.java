package com.innovate.modules.check.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.check.dao.InnovateCheckInfoDao;
import com.innovate.modules.check.entity.InnovateCheckAttachEntity;
import com.innovate.modules.check.entity.InnovateCheckInfoEntity;
import com.innovate.modules.check.entity.InnovateCheckInfoModel;
import com.innovate.modules.check.service.InnovateCheckAttachService;
import com.innovate.modules.check.service.InnovateCheckInfoService;
import com.innovate.modules.check.service.InnovateCheckRetreatService;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.entity.DeclareInfoModel;
import com.innovate.modules.declare.service.DeclareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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


    private List<InnovateCheckInfoModel> innovateCheckInfoModels;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Integer totalPage  = baseMapper.queryCountPage(params);

        Integer currPage  = 1;
        Integer pageSize  = 10;
        try {
            if (params.get("currPage")!=null&&params.get("pageSize")!=null) {
                currPage = Integer.parseInt(params.get("currPage").toString());
                pageSize = Integer.parseInt(params.get("pageSize").toString());
            }
        } catch (Exception e) { e.printStackTrace(); }
        Integer startPage = 0 + pageSize * (currPage - 1);
        Integer endPage = pageSize;

        params.put("startPage", startPage);
        params.put("endPage", endPage);

        List<InnovateCheckInfoEntity> innovateCheckInfoEntities = baseMapper.queryPage(params);
        innovateCheckInfoModels = new LinkedList<>();
        InnovateCheckInfoModel temp = null;
        for (InnovateCheckInfoEntity info:innovateCheckInfoEntities){
            temp = new InnovateCheckInfoModel();
            DeclareInfoEntity declareInfoEntity = declareInfoService.queryById(info.getDeclareId());
            temp.setInnovateCheckInfoEntity(info);
            temp.setDeclareInfoEntity(declareInfoEntity);
            innovateCheckInfoModels.add(temp);
        }

        return new PageUtils(innovateCheckInfoModels, totalPage, pageSize, currPage);
    }

    /**
     * 通过id批量设置需要中期检查的项目
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
     * @param time
     */
    @Override
    public void saveByTime(Date time) {
        EntityWrapper ew = new EntityWrapper<>();
        ew.setEntity(new DeclareInfoEntity());
        ew.like("declare_time",time.toString(), SqlLike.DEFAULT);
        List<DeclareInfoEntity> declareInfoEntities = declareInfoService.selectList(ew);

        for (DeclareInfoEntity declareInfoEntity:declareInfoEntities){
            InnovateCheckInfoEntity innovateCheckInfoEntity = new InnovateCheckInfoEntity();
            innovateCheckInfoEntity.setInstituteId(declareInfoEntity.getInstituteId());
            innovateCheckInfoEntity.setDeclareId(declareInfoEntity.getDeclareId());
            this.insert(innovateCheckInfoEntity);
        }
    }

    /**
     * 审批
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
        if (innovateCheckInfoModel.getInnovateCheckAttachEntities()!=null)
        insertOrUpdate(innovateCheckInfoModel.getInnovateCheckInfoEntity());
        if (innovateCheckInfoModel.getInnovateCheckRetreatEntities()!=null)
            innovateCheckRetreatService.insertOrUpdateBatch(innovateCheckInfoModel.getInnovateCheckRetreatEntities());

    }

}