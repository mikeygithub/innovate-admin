package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntStudentAttachmentDao;
import com.innovate.modules.enterprise.entity.EntStudentAttachmentEntity;
import com.innovate.modules.enterprise.service.EntStudentAttachmentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entStudentAttachmentService")
public class EntStudentAttachmentServiceImpl extends ServiceImpl<EntStudentAttachmentDao, EntStudentAttachmentEntity> implements EntStudentAttachmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntStudentAttachmentEntity> page = this.selectPage(
                new Query<EntStudentAttachmentEntity>(params).getPage(),
                new EntityWrapper<EntStudentAttachmentEntity>()
        );

        return new PageUtils(page);
    }

}