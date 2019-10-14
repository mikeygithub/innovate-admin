package com.innovate.modules.enterprise.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 项目信息表
 * 
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@Mapper
public interface EntProjectInfoDao extends BaseMapper<EntProjectInfoEntity> {

    /**
     * 根据项目id和项目类型 查询项目信息列表 学生
     * @param params
     * @return
     */
    List<EntProjectInfoEntity> queryEntProjectInfoListByIdPerId(Map<String, Object> params);

    /**
     * 根据项目id和项目类型 查询项目信息列表 教师
     * @param params
     * @return
     */
    List<EntProjectInfoEntity> queryEntProjectInfoListByIdTeacherId(Map<String, Object> params);

    /**
     * 根据项目id和项目类型 查询项目信息列表 企业
     * @param params
     * @return
     */
    List<EntProjectInfoEntity> queryEntProjectInfoListByIdEntId(Map<String, Object> params);

    /**
     * 根据项目id和项目类型 查询项目信息 学生
     * @param id
     * @return
     */
    EntProjectInfoEntity queryEntProjectInfoByIdPerId(Long id);

    /**
     * 根据项目id和项目类型 查询项目信息 教师
     * @param id
     * @return
     */
    EntProjectInfoEntity queryEntProjectInfoByIdTeacherId(Long id);

    /**
     * 根据项目id和项目类型 查询项目信息 企业
     * @param id
     * @return
     */
    EntProjectInfoEntity queryEntProjectInfoByIdEntId(Long id);

    /**
     * 处理项目信息审核
     * @param params
     * @return
     */
    boolean updateEntExamine(Map<String, Object> params);

}
