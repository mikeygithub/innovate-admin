package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.InnovateGradeEntity;
import com.innovate.modules.innovate.entity.InnovateSchoolEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Mapper
public interface InnovateSchoolDao extends BaseMapper<InnovateSchoolEntity> {
    List<InnovateSchoolEntity> queryAll();
}
