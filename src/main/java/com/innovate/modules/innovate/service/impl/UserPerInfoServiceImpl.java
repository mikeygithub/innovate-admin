package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.UserPersonInfoDao;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:40
 * @Version 1.0
 */
@Service
public class UserPerInfoServiceImpl extends ServiceImpl<UserPersonInfoDao, UserPersonInfoEntity> implements UserPerInfoService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserPerInfoService userPerInfoService;

    @Override
    public List<UserPersonInfoEntity> queryAllPersonInfo(Map<String, Object> params) {
        return baseMapper.queryAllPersonInfo(params);
    }

    @Override
    public Long deleteByProjectId(Long projectId) {
        return baseMapper.deleteByProjectId(projectId);
    }

    @Override
    public UserPersonInfoEntity queryByUserId(Long userId) {
        UserPersonInfoEntity userPersonInfoEntity = baseMapper.queryByUserId(userId);
        SysUserEntity sysUserEntity = sysUserService.selectById(userId);
        if (null == userPersonInfoEntity) {
            userPersonInfoEntity = new UserPersonInfoEntity();
        }
        userPersonInfoEntity.setSysUserEntity(sysUserEntity);

        return userPersonInfoEntity;
    }

    @Override
    public void saveOrUpdate(UserPersonInfoEntity userPersonInfoEntity) {
        System.out.println("这是" + new Gson().toJson(userPersonInfoEntity));;
        SysUserEntity sysUserEntity = userPersonInfoEntity.getSysUserEntity();
        userPersonInfoEntity.setSysUserEntity(null);
        this.insertOrUpdate(userPersonInfoEntity);
        sysUserService.insertOrUpdate(sysUserEntity);
    }

    /**
     * 根据学院id查找该学院下的全部负责人
     * @param instituteId
     * @return
     */
    @Override
    public List<UserPersonInfoEntity> queryByUserInstituteIds(Long instituteId) {
        return baseMapper.queryByUserInstituteIds(instituteId);
    }

    @Override
    public Long queryUserPerIdByUserId(Long userId) {
        return baseMapper.queryUserPerIdByUserId(userId);
    }
}
