package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.enterprise.dao.EntProjectMemberDao;
import com.innovate.modules.enterprise.entity.EntProjectMemberEntity;
import com.innovate.modules.enterprise.service.EntProjectMemberService;


@Service("entProjectMemberService")
public class EntProjectMemberServiceImpl extends ServiceImpl<EntProjectMemberDao, EntProjectMemberEntity> implements EntProjectMemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntProjectMemberEntity> page = this.selectPage(
                new Query<EntProjectMemberEntity>(params).getPage(),
                new EntityWrapper<EntProjectMemberEntity>()
        );

        return new PageUtils(page);
    }

}