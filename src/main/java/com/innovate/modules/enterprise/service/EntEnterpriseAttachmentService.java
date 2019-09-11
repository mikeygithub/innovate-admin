package com.innovate.modules.enterprise.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.enterprise.entity.EntEnterpriseAttachmentEntity;

import java.util.Map;

/**
 * 企业附件表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
public interface EntEnterpriseAttachmentService extends IService<EntEnterpriseAttachmentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

