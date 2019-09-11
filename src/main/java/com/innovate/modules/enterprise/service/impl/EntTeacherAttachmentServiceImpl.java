package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntTeacherAttachmentDao;
import com.innovate.modules.enterprise.entity.EntTeacherAttachmentEntity;
import com.innovate.modules.enterprise.service.EntTeacherAttachmentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entTeacherAttachmentService")
public class EntTeacherAttachmentServiceImpl extends ServiceImpl<EntTeacherAttachmentDao, EntTeacherAttachmentEntity> implements EntTeacherAttachmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntTeacherAttachmentEntity> page = this.selectPage(
                new Query<EntTeacherAttachmentEntity>(params).getPage(),
                new EntityWrapper<EntTeacherAttachmentEntity>()
        );

        return new PageUtils(page);
    }

}