package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.InnovateInstituteEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
public interface InnovateInstituteService extends IService<InnovateInstituteEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<InnovateInstituteEntity> queryAllInstitute();
    @Transactional
    void total(Map<String, Object> params);

    InnovateInstituteEntity queryByInstituteId(Map<String, Object> params);
}
