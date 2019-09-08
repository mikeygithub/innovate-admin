package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.finish.dao.FinishTeacherDao;
import com.innovate.modules.finish.entity.FinishTeacherEntity;
import com.innovate.modules.finish.service.FinishTeacherService;
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
public class FinishTeacherServiceImpl extends ServiceImpl<FinishTeacherDao, FinishTeacherEntity> implements FinishTeacherService {

    @Override
    public List<FinishTeacherEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
