package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.match.dao.MatchTeacherDao;
import com.innovate.modules.match.entity.MatchTeacherEntity;
import com.innovate.modules.match.service.MatchTeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:51
 * @Version 1.0
 */
@Service
public class MatchTeacherServiceImpl extends ServiceImpl<MatchTeacherDao, MatchTeacherEntity> implements MatchTeacherService {

    @Override
    public List<MatchTeacherEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
