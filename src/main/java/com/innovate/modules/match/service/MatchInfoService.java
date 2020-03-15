package com.innovate.modules.match.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.match.entity.MatchInfoEntity;
import com.innovate.modules.match.entity.MatchInfoModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:10
 * @Version 1.0
 */
public interface MatchInfoService extends IService<MatchInfoEntity> {
    /**
     * 获得项目ID
     */
    MatchInfoEntity queryById(Long matchId);

    /**
     * 通过名称获得项目
     */
    List<MatchInfoEntity> queryByName(Map<String, Object> params);

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
    List<MatchInfoEntity> queryPage(Map<String, Object> params);

    List<MatchInfoEntity> noPass(Map<String, Object> params);

    //统计项目个数
    Long queryProjectNum(Map<String, Object> params);

    //统计负责人个数
    Long queryUserNum(Map<String, Object> params);

    /**
     * 删除项目
     * @param params
     */
    void remove(Map<String, Object> params);
}
