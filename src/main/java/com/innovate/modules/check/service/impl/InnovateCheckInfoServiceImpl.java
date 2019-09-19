package com.innovate.modules.check.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.check.dao.InnovateCheckInfoDao;
import com.innovate.modules.check.entity.InnovateCheckAttachEntity;
import com.innovate.modules.check.entity.InnovateCheckInfoEntity;
import com.innovate.modules.check.service.InnovateCheckInfoService;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.service.DeclareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;



@Service("innovateCheckInfoService")
public class InnovateCheckInfoServiceImpl extends ServiceImpl<InnovateCheckInfoDao, InnovateCheckInfoEntity> implements InnovateCheckInfoService {
    @Autowired
    private DeclareInfoService declareInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateCheckInfoEntity> page = this.selectPage(
                new Query<InnovateCheckInfoEntity>(params).getPage(),
                new EntityWrapper<InnovateCheckInfoEntity>()
        );
        return new PageUtils(page);
    }

    /**
     * 通过id批量设置需要中期检查的项目
     * @param checkIds
     */
    @Override
    public void saveByDeclareBatchIds(Long[] checkIds) {
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

}