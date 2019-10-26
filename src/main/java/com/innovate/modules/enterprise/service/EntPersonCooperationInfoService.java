package com.innovate.modules.enterprise.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntPersonCooperationInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 项目合作人表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
public interface EntPersonCooperationInfoService extends IService<EntPersonCooperationInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     *  根据项目合作信息id查询合作关系人员信息
     * @param proCooperationInfoId
     * @return
     */
    List<EntPersonCooperationInfoEntity> queryPersonCooperationInfoByProCooperationInfoId(Long proCooperationInfoId);

    /**
     * 保存项目合作关系
     * @param proInfoId
     * @return
     */
    R savePersonCooper(Long proInfoId, Long userId);

    /**
     * 更新用户合作关系
     * @param params
     * @return
     */
    R updatePersonCooperation(Map<String, Object> params);

}

