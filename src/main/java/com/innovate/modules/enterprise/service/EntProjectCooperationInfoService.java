package com.innovate.modules.enterprise.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目合作信息表
 *
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
public interface EntProjectCooperationInfoService extends IService<EntProjectCooperationInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 项目合作信息 详情
     * @param params
     * @return
     */
    R queryProjectCooperationInfo(Map<String, Object> params);

    /**
     * 项目合作审核
     * @param params
     * @return
     */
    R updateProjectExamine(Map<String, Object> params);

    /**
     * 根据项目id查询合作信息
     * @param projectId
     * @return
     */
    EntProjectCooperationInfoEntity queryEntProjectCooperationInfoByProjectId(Long projectId);

    /**
     * 新增项目合作关系
     * @param entProjectCooperationInfo
     * @return
     */
    R insertProjectCooperation(EntProjectCooperationInfoEntity entProjectCooperationInfo);

    /**
     * 根据类型获取相关合作项目
     * @param type
     * @param id
     * @return
     */
    List<Long> queryProjectInfoIdByType(String type, long id);

    /**
     * 获取指定inApply的项目id
     * @param inApply
     * @return
     */
    List<Long> queryProInfoIdsByInApply(String inApply);

    ArrayList<Long> queryProInfoIdsByProInfoId(ArrayList<Long> projectIds);
    
}

