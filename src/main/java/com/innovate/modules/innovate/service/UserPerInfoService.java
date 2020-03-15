package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:13
 * @Version 1.0
 */
public interface UserPerInfoService extends IService<UserPersonInfoEntity> {
    List<UserPersonInfoEntity> queryAllPersonInfo(Map<String, Object> params);

    UserPersonInfoEntity queryByUserId(Long userId);
    Long deleteByProjectId(Long projectId);
    @Transactional
    void saveOrUpdate(UserPersonInfoEntity userPersonInfoEntity);


    List<UserPersonInfoEntity> queryByUserInstituteIds(Long instituteId);

    /**
     * 用户id 查学生id
     * @param userId
     * @return
     */
    Long queryUserPerIdByUserId(Long userId);

}
