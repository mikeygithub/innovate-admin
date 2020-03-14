package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.InnovateFileAskEntity;

import java.util.List;
import java.util.Map;

/**
 * 上传文件要求表
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
public interface InnovateFileAskService extends IService<InnovateFileAskEntity> {

    PageUtils queryPage(Map<String, Object> params);
    InnovateFileAskEntity queryByParams(Map<String, Object> params);
}

