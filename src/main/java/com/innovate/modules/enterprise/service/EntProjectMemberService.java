package com.innovate.modules.enterprise.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.enterprise.entity.EntProjectMemberEntity;

import java.util.Map;

/**
 * 项目合作成员
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-11-09 14:05:55
 */
public interface EntProjectMemberService extends IService<EntProjectMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

