package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.InnovateSchoolEntity;
import com.innovate.modules.innovate.entity.InnovateTitleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Mapper
public interface InnovateTitleDao extends BaseMapper<InnovateTitleEntity> {
    List<InnovateTitleEntity> queryAll();
}
