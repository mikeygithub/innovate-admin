package com.innovate.modules.enterprise.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 项目信息表
 *
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
public interface EntProjectInfoService extends IService<EntProjectInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据项目id和项目类型 查询项目信息 学生
     * @param id
     * @return
     */
    R queryEntProjectInfoByIdPerId(Long id);

    /**
     * 根据项目id和项目类型 查询项目信息 教师
     * @param id
     * @return
     */
    R queryEntProjectInfoByIdTeacherId(Long id);

    /**
     * 根据项目id和项目类型 查询项目信息 企业
     * @param id
     * @return
     */
    R queryEntProjectInfoByIdEntId(Long id);

    /**
     * 处理项目信息审核
     * @param params
     * @return
     */
    R updateEntExamine(Map<String, Object> params);

}

