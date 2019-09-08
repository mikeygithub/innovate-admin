package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.ProjectInfoEntity;
import com.innovate.modules.innovate.entity.ProjectInfoModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:10
 * @Version 1.0
 */
public interface ProjectInfoService extends IService<ProjectInfoEntity> {

    /**
     * 更新企业状态并通过审批（1在驻，2成功，3失败）
     * @param projectInfoModel
     */
    @Transactional
    void status(ProjectInfoModel projectInfoModel);

    /**
     * 获得项目ID
     */
    ProjectInfoEntity queryById(Long projectId);

    /**
     * 查询满足条件的项目总数
     * @param params
     * @return
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 查询满足条件的项目
     * @param params
     * @return
     */
    List<ProjectInfoEntity> queryPage(Map<String, Object> params);

    //查询所有的项目
    List<ProjectInfoEntity> queryAll(Map<String, Object> params);

    List<ProjectInfoEntity> noPass(Map<String, Object> params);

    /**
     * 删除项目
     * @param params
     */
    void remove(Map<String, Object> params);

    /**
     * 更新工位号并通过审批
     */
    @Transactional
    void station(ProjectInfoModel projectInfoModel);
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
