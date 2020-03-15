package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.finish.service.FinishInExpertService;
import com.innovate.modules.finish.service.FinishOutExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.finish.dao.FinishExpertCollectDao;
import com.innovate.modules.finish.entity.FinishExpertCollectEntity;
import com.innovate.modules.finish.service.FinishExpertCollectService;


@Service("innovateFinishExpertCollectService")
public class FinishExpertCollectServiceImpl extends ServiceImpl<FinishExpertCollectDao, FinishExpertCollectEntity> implements FinishExpertCollectService {

    @Autowired
    private FinishOutExpertService finishOutExpertService;
    @Autowired
    private FinishInExpertService finishInExpertService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        EntityWrapper<FinishExpertCollectEntity> ew = new EntityWrapper<>();
        ew.setEntity(new FinishExpertCollectEntity());

        String key = params.get("key").toString();
        Object instituteId = params.get("instituteId");
        Object year = params.get("year");
        Object status = params.get("status");

        if (key!=null&&key!="")ew.like("expert_collect_writer",key, SqlLike.DEFAULT);
        if (year!=null&&year!="")ew.like("expert_collect_time",year.toString(),SqlLike.DEFAULT);
        if (instituteId!=null&&instituteId!="")ew.eq("expert_collect_institute_id",instituteId);
        if (status!=null&&status!="")ew.eq("expert_apply",status);

        Page<FinishExpertCollectEntity> page = this.selectPage(
                new Query<FinishExpertCollectEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

    /**
     * 更新
     * @param innovateFinishExpertCollect
     */
    @Override
    public void updateByProps(FinishExpertCollectEntity innovateFinishExpertCollect) {

        this.updateById(innovateFinishExpertCollect);

        finishInExpertService.insertOrUpdateAllColumnBatch(innovateFinishExpertCollect.getFinishInExpertEntities());

        finishOutExpertService.insertOrUpdateBatch(innovateFinishExpertCollect.getFinishOutExpertEntities());
    }

}