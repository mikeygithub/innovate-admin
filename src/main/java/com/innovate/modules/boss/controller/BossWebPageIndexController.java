package com.innovate.modules.boss.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 前端主页面控制器
 * @date 2019/10/15
 */
@RestController
@RequestMapping("webpage/")
public class BossWebPageIndexController {

    @Autowired
    private EntProjectInfoService entProjectInfoService;

    @RequestMapping("projectInfos")
    public R projectInfos(@RequestParam Map<String,Object> params){
        return  entProjectInfoService.queryWebEntProjectInfos(params);
    }

    /**
     * 项目详情
     * @param projectId
     * @return
     */
    @RequestMapping("projectInfo/{projectId}")
    public R projectInfo(@PathVariable("projectId")Long projectId){
        return entProjectInfoService.queryWebEntProjectInfo(projectId, "1");
    }

}
