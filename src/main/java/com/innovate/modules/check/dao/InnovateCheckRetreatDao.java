package com.innovate.modules.check.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.check.entity.InnovateCheckRetreatEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 中期检查回退
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@Mapper
public interface InnovateCheckRetreatDao extends BaseMapper<InnovateCheckRetreatEntity> {

    List<InnovateCheckRetreatEntity> queryByParams(Map<String, Object> params);

}
