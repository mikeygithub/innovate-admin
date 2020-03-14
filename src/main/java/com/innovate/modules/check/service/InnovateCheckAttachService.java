package com.innovate.modules.check.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.check.entity.InnovateCheckAttachEntity;

import java.util.List;
import java.util.Map;

/**
 * 中期检查附件表
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
public interface InnovateCheckAttachService extends IService<InnovateCheckAttachEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<InnovateCheckAttachEntity> queryByCheckId(Long checkId);
}

