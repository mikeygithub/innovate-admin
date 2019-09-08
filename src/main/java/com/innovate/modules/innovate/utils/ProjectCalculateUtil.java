package com.innovate.modules.innovate.utils;

import com.innovate.modules.innovate.common.ProjectCalculate;
import com.innovate.modules.innovate.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/9
 **/
@Component
public class ProjectCalculateUtil {

    private static Set<ProjectCalculate> projectCalculates = new HashSet<ProjectCalculate>();
    private static Long projectId;
    private static Long baseId;
    @Autowired
    private static ProjectCalculateUtil util;
    @Autowired
    private ProjectInfoService innovateProjectInfoService;

    //通过init方法，
    //1.注入bean(innovateProjectInfoService)
    //2.赋值给static ProjectCalculateUtil util
    //3.innovateProjectInfoService，就通过util来取
    @PostConstruct
    public void init(){
        util = this;
        util.innovateProjectInfoService = this.innovateProjectInfoService;
    }

    //添加计算对象
    public static void addObject(ProjectCalculate projectCalculate) {
        projectCalculates.add(projectCalculate);
    }
    //计算
    public static void calculate() {
        for (ProjectCalculate projectCalculate : projectCalculates) {
            projectCalculate.calculate(projectId, baseId);
        }
    }

    public static void setCalculateId(Long projectId) {
        if (null != projectId){
            ProjectCalculateUtil.projectId = projectId;
            ProjectCalculateUtil.baseId = util.innovateProjectInfoService.queryBaseId(projectId);
        }else{
            ProjectCalculateUtil.projectId = null;
            ProjectCalculateUtil.baseId = null;

        }

    }

}
