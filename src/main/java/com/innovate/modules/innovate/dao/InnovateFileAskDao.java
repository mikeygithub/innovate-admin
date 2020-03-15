package com.innovate.modules.innovate.dao;

import com.innovate.modules.innovate.entity.InnovateFileAskEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 上传文件要求表
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@Mapper
public interface InnovateFileAskDao extends BaseMapper<InnovateFileAskEntity> {

    /**
     * 查询满足条件的项目总数
     * @param params
     * @return
     */
    Integer queryCountPage(Map<String, Object> params);

    List<InnovateFileAskEntity> queryPage(Map<String, Object> params);

    /**
     * 查询满足条件的对象
     * @param params
     * @return
     */
    InnovateFileAskEntity queryByParams(Map<String, Object> params);
}
