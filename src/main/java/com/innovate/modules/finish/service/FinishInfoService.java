package com.innovate.modules.finish.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.finish.entity.FinishInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:10
 * @Version 1.0
 */
public interface FinishInfoService extends IService<FinishInfoEntity> {
    /**
     * 获得项目ID
     */
    FinishInfoEntity queryById(Long finishId);

    /**
     * 查询满足条件的项目总数
     * @param params
     * @return
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 查询满足条件的项目
     * @param params
     * @return
     */
    List<FinishInfoEntity> queryPage(Map<String, Object> params);

    List<FinishInfoEntity> noPass(Map<String, Object> params);

    /**
     * 删除项目
     * @param params
     */
    void remove(Map<String, Object> params);
}
