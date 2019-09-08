package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.ProjectSubMoneyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:25
 * @Version 1.0
 */
@Mapper
public interface ProjectSubMoneyDao extends BaseMapper<ProjectSubMoneyEntity> {

    List<ProjectSubMoneyEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
    /**
     * 统计补贴金额
     */
    Double querySubMoney(Long subType,Long baseId);

    /**
     * 统计获得投融资团队的数量
     */
    Long queryInvestNum(Long subType,Long baseId);
}
