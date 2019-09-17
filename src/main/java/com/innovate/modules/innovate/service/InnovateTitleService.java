package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.InnovateSubjectEntity;
import com.innovate.modules.innovate.entity.InnovateTitleEntity;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
public interface InnovateTitleService extends IService<InnovateTitleEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<InnovateTitleEntity> queryTitle(Map<String, Object> params);
    List<InnovateTitleEntity> queryAll();
}
