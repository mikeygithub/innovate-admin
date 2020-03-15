package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.ProjectInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:18
 * @Version 1.0
 */
@Mapper
public interface ProjectInfoDao extends BaseMapper<ProjectInfoEntity> {

    /**
     * 获得项目ID
     */
    ProjectInfoEntity queryById(Long projectId);

    Integer queryCountPage(Map<String, Object> params);

    List<ProjectInfoEntity> queryPage(Map<String, Object> params);

    List<ProjectInfoEntity> queryAll(Map<String, Object> params);

    List<ProjectInfoEntity> noPass(Map<String, Object> params);

    void remove(Map<String, Object> params);



//    统计开始

    /**
     * 查询工位Id
     * projectStatus-->项目状态（1在驻，2成功，3失败）
     */
    List<Long> queryStationIdList(Long projectStatus);

    /**
     *    统计项目总数
     *    参数：projectStatus-->项目状态（1在驻B0，2成功C0，3失败D0）
     *          projectBase-->是否有见习基地：1有，2否
     *          projectRegStatus-->是否有工商注册情况：1是，2否
     *          baseId-->所属基地编号
     */
    Long queryProjectNum(Long projectStatus, Long projectBase, Long projectRegStatus,Long baseId);

    /**
     *    统计总投资额
     */
    Double queryInvest();

    /**
     *    统计吸纳就业见习
     */
    Long queryAbsorb(Long baseId);

    /**
     *    统计带动就业
     */
    Long queryDriveEmNum(Long baseId);

    /**
     *    统计拥有的有效知识产权数量
     */
    Long queryIprNum(Long projectProperty,Long baseId);

    /**
     *    获取baseId
     */
    Long queryBaseId(Long projectId);
}
