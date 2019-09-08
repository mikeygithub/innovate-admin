package com.innovate.modules.declare.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.declare.entity.DeclareAttachEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:41
 * @Version 1.0
 */
public interface DeclareAttachService extends IService<DeclareAttachEntity> {

    List<DeclareAttachEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
