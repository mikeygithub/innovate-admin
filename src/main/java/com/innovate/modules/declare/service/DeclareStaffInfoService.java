package com.innovate.modules.declare.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.declare.entity.DeclareStaffInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:15
 * @Version 1.0
 */
public interface DeclareStaffInfoService extends IService<DeclareStaffInfoEntity> {

    List<DeclareStaffInfoEntity> queryAll(Map<String, Object> params);

    //统计参与者个数
    Long queryStaffNum(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
