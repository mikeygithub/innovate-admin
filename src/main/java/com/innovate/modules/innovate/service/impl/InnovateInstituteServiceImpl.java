package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.service.DeclareInfoService;
import com.innovate.modules.declare.service.DeclareStaffInfoService;
import com.innovate.modules.declare.service.DeclareTeacherService;
import com.innovate.modules.innovate.dao.InnovateInstituteDao;
import com.innovate.modules.innovate.entity.InnovateInstituteEntity;
import com.innovate.modules.innovate.service.InnovateInstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Service
public class InnovateInstituteServiceImpl extends ServiceImpl<InnovateInstituteDao, InnovateInstituteEntity> implements InnovateInstituteService {

    @Autowired
    private InnovateInstituteService instituteService;
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private DeclareStaffInfoService declareStaffInfoService;
    @Autowired
    private DeclareTeacherService declareTeacherService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateInstituteEntity> page = this.selectPage(
                new Query<InnovateInstituteEntity>(params).getPage(),
                new EntityWrapper<InnovateInstituteEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<InnovateInstituteEntity> queryAllInstitute() {
        return baseMapper.queryAllInstitute();
    }

    @Override
    public void total(Map<String, Object> params) {
        InnovateInstituteEntity instituteEntity = instituteService.queryByInstituteId(params);

        //统计申报项目总数
        Long DeclareProject = declareInfoService.queryDeclareProjectNum(params);
        //统计申报创新训练项目数
        Long NewProject = declareInfoService.queryNewProjectNum(params);
        //统计申报创业训练项目数
        Long TrainProject = declareInfoService.queryTrainProjectNum(params);
        //统计申报创业实践项目数
        Long PracticeProject = declareInfoService.queryPracticeProjectNum(params);
        //统计参与申报项目学生数
        Long studentNum = declareStaffInfoService.queryStaffNum(params);
        //统计参与申报项目导师数
        Long teacherNum = declareTeacherService.queryTeacherNum(params);

        instituteEntity.setDeclareProjectNum(DeclareProject);
        instituteEntity.setDeclareNewProjectNum(NewProject);
        instituteEntity.setDeclareTrainProjectNum(TrainProject);
        instituteEntity.setDeclarePracticeProjectNum(PracticeProject);
        instituteEntity.setDeclareStudentNum(studentNum);
        instituteEntity.setDeclareTeacherNum(teacherNum);

        instituteService.insertOrUpdate(instituteEntity);
    }

    @Override
    public InnovateInstituteEntity queryByInstituteId(Map<String, Object> params) {
        return baseMapper.queryByInstituteId(params);
    }
}
