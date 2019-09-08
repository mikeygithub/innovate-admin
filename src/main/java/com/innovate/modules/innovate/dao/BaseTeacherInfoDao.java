package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.BaseTeacherInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:07
 * @Version 1.0
 */
@Mapper
public interface BaseTeacherInfoDao extends BaseMapper<BaseTeacherInfoEntity> {
    /**
     *  统计教师人数
     *  参数：任职情况：1-专职，2-兼职
     */
    Long queryAllTeacher(Long baseId);
    Long queryTypeTeacher(Long baseId, Long status);
}
