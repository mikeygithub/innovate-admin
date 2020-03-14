package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.match.dao.MatchAttachDao;
import com.innovate.modules.match.entity.MatchAttachEntity;
import com.innovate.modules.match.service.MatchAttachService;
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
public class MatchAttachServiceImpl extends ServiceImpl<MatchAttachDao, MatchAttachEntity> implements MatchAttachService {
    @Override
    public List<MatchAttachEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
