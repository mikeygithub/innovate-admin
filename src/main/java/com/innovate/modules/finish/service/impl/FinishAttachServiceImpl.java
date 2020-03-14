package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.finish.entity.FinishAttachEntity;
import com.innovate.modules.finish.dao.FinishAttachDao;
import com.innovate.modules.finish.service.FinishAttachService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:47
 * @Version 1.0
 */
@Service
public class FinishAttachServiceImpl extends ServiceImpl<FinishAttachDao, FinishAttachEntity> implements FinishAttachService {
    @Override
    public List<FinishAttachEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
