package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.ProjectSubMoneyEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:18
 * @Version 1.0
 */
public interface ProjectSubMoneyService extends IService<ProjectSubMoneyEntity> {

    List<ProjectSubMoneyEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

    /**
     * 统计补贴金额
     * 参数：补助类型：1投资，2管理服务，3房租和宽带，4水电费，5一次性创业，6吸纳困难群体，7社会保险，8创业担保贷款
     */
    Double querySubMoney(Long subType, Long baseId);

    /**
     * 统计获得投融资团队的数量
     */
    Long queryInvestNum(Long subType,Long baseId);

}
