package com.innovate.modules.check.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.Query;
import com.innovate.modules.check.dao.InnovateCheckAttachDao;
import com.innovate.modules.check.entity.InnovateCheckAttachEntity;
import com.innovate.modules.check.service.InnovateCheckAttachService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.innovate.common.utils.PageUtils;



@Service("innovateCheckAttachService")
public class InnovateCheckAttachServiceImpl extends ServiceImpl<InnovateCheckAttachDao, InnovateCheckAttachEntity> implements InnovateCheckAttachService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateCheckAttachEntity> page = this.selectPage(
                new Query<InnovateCheckAttachEntity>(params).getPage(),
                new EntityWrapper<InnovateCheckAttachEntity>()
        );
        return new PageUtils(page);
    }
    @Override
    public List<InnovateCheckAttachEntity> queryByCheckId(Long checkId){
        EntityWrapper ew = new EntityWrapper<>();
        ew.setEntity(new InnovateCheckAttachEntity());
        ew.eq("check_id",checkId);
        ew.eq("is_del",0);
        return this.selectList(ew);
    }
}