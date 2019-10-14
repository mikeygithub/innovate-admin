package com.innovate.modules.enterprise.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.enterprise.entity.EntPersonCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目合作人表
 * 
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@Mapper
public interface EntPersonCooperationInfoDao extends BaseMapper<EntPersonCooperationInfoEntity> {

    /**
     * 项目合作人表 - 合作关系 - 学生
     * @param params
     * @return
     */
    EntProjectCooperationInfoEntity queryProjectCooperationInfosForPer(java.util.Map<String,Object> params);

    /**
     * 项目合作人表 - 合作关系 - 教师
     * @param params
     * @return
     */
    EntProjectCooperationInfoEntity queryProjectCooperationInfosForTeacher(java.util.Map<String,Object> params);

    /**
     * 项目合作人表 - 合作关系 - 企业
     * @param params
     * @return
     */
    EntProjectCooperationInfoEntity queryProjectCooperationInfosForEnt(java.util.Map<String,Object> params);


}
