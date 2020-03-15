package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.InnovateInstituteEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Mapper
public interface InnovateInstituteDao extends BaseMapper<InnovateInstituteEntity> {

    InnovateInstituteEntity queryByInstituteId(Map<String, Object> params);

    List<InnovateInstituteEntity> queryAllInstitute();
}
