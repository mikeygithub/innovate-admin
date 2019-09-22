package com.innovate.modules.check.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.check.entity.InnovateCheckInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 中期检查表
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@Mapper
public interface InnovateCheckInfoDao extends BaseMapper<InnovateCheckInfoEntity> {

    Integer queryCountPage(Map<String, Object> params);

    List<InnovateCheckInfoEntity> queryPage(Map<String, Object> params);

}
