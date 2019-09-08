package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.common.BaseCalculate;
import com.innovate.modules.innovate.dao.BaseTeacherInfoDao;

import com.innovate.modules.innovate.entity.BaseInfoEntity;
import com.innovate.modules.innovate.entity.BaseTeacherInfoEntity;
import com.innovate.modules.innovate.service.BaseInfoService;
import com.innovate.modules.innovate.service.BaseTeacherInfoService;
import com.innovate.modules.innovate.utils.BaseCalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:51
 * @Version 1.0
 */
@Service
public class BaseTeacherInfoServiceImpl extends ServiceImpl<BaseTeacherInfoDao, BaseTeacherInfoEntity> implements BaseCalculate, BaseTeacherInfoService {

    @Autowired
    private BaseInfoService baseInfoService;

    BaseTeacherInfoServiceImpl() {
        BaseCalculateUtil.addObject(this);
    }
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaseTeacherInfoEntity> page = this.selectPage(
                new Query<BaseTeacherInfoEntity>(params).getPage(),
                new EntityWrapper<BaseTeacherInfoEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public Long queryAllTeacher(Long baseId) {
        return baseMapper.queryAllTeacher(baseId);
    }

    @Override
    public Long queryTypeTeacher(Long baseId, Long status) {
        return baseMapper.queryTypeTeacher(baseId, status);
    }

    @Override
    public void calculate(Long baseId) {
        BaseInfoEntity baseInfoEntity = baseInfoService.selectById(baseId);
        //教师总数
        Long allTeacherNum = this.queryAllTeacher(baseId);
        if (null == allTeacherNum) {
            allTeacherNum = 0L;
        }
        baseInfoEntity.setBaseAllTeacherNum(allTeacherNum);

        //全职教师总数
        Long fullTeacherNum = this.queryTypeTeacher(baseId, 1L);
        if (null == fullTeacherNum) {
            fullTeacherNum = 0L;
        }
        baseInfoEntity.setBaseFullTeacherNum(fullTeacherNum);

        //兼职教师总数
        Long partTeacherNum = this.queryTypeTeacher(baseId, 2L);
        if (null == partTeacherNum) {
            partTeacherNum = 0L;
        }
        baseInfoEntity.setBasePartTeacherNum(partTeacherNum);

        baseInfoService.updateAllColumnById(baseInfoEntity);
    }
}
