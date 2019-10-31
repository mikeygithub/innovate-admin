package com.innovate.modules.enterprise.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.enterprise.entity.EntPersonCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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


    /**
     *  根据项目合作信息id查询合作关系人员信息
     * @param proCooperationInfoId
     * @return
     */
    List<EntPersonCooperationInfoEntity> queryPersonCooperationInfoByProCooperationInfoId(Long proCooperationInfoId);

    /**
     *  根据项目合作信息id查询合作关系人员信息
     * @param proCooperationInfoId
     * @return
     */
    List<EntPersonCooperationInfoEntity> queryPersonCooperationInfoByProCooperationInfoIdAndApply(Long proCooperationInfoId, String inApply);

    /**
     * 更新合作者状态
     * @param id
     * @param typeId
     * @param inApply
     */
    void updatePersonCooperation(@Param("id") Long id, @Param("typeId") String typeId, @Param("inApply") String inApply);


    /**
     * 删除合作人员
     * @param proCooperationInfoId
     * @return
     */
    boolean deleteByProCooperationInfoId(Long proCooperationInfoId);

}
