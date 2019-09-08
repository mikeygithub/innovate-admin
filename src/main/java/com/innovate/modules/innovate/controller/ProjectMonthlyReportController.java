package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.entity.ProjectMonthlyReportEntity;
import com.innovate.modules.innovate.entity.ProjectMonthlyReportTotalEntity;
import com.innovate.modules.innovate.service.ProjectMonthlyReportService;
import com.innovate.modules.innovate.service.ProjectMonthlyReportTotalService;
import com.innovate.modules.match.entity.MatchEventEntity;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author:tz
 * @create:2019-01-08
 * @description:项目月报表
 **/
@RestController
@RequestMapping("innovate/project/report")
public class ProjectMonthlyReportController extends AbstractController {

    @Autowired
    private ProjectMonthlyReportService projectMonthlyReportService;
    @Autowired
    private ProjectMonthlyReportTotalService projectMonthlyReportTotalService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:report:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectMonthlyReportService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{reportId}")
    @RequiresPermissions("innovate:report:info")
    public R info(@PathVariable("reportId") Integer reportId){
        ProjectMonthlyReportEntity monthlyReport = projectMonthlyReportService.selectById(reportId);

        return R.ok().put("monthlyReport", monthlyReport);
    }

    /**
     * 统计
     */
    @PostMapping("/total")
    @RequiresPermissions("innovate:report:total")
    public R total(@RequestParam Map<String, Object> params){
        ProjectMonthlyReportTotalEntity projectMonthlyReportTotalEntity = null;
        if ("{}".equals(params) && params == null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
            Timestamp totalStartTime = Timestamp.valueOf(df.format(new Date()));
            Timestamp totalEndTime = Timestamp.valueOf(df.format(new Date()));
            params = new HashMap<>();
            params.put("totalStartTime",totalStartTime);
            params.put("totalEndTime",totalEndTime);

            //得所有项目id
            List<Long> projectIds = new ArrayList<>();
            List<ProjectMonthlyReportEntity> projectMonthlyReportEntityList = projectMonthlyReportService.queryAllReport();
            for (ProjectMonthlyReportEntity projectMonthlyReportEntity : projectMonthlyReportEntityList) {
                projectIds.add(projectMonthlyReportEntity.getProjectId());
            }
            //封装params
            for (Long projectId : projectIds) {
                params.put("projectId", projectId);
                //先统计
                projectMonthlyReportTotalService.total(params);
                params = new HashMap<>();
                params.put("totalStartTime",totalStartTime);
                params.put("totalEndTime",totalEndTime);
            }
        }else{
             projectMonthlyReportTotalEntity = projectMonthlyReportTotalService.total(params);
        }

        PageUtils page = projectMonthlyReportService.queryPage(params);
        return R.ok().put("page", page).put("projectMonthlyReportTotalEntity", projectMonthlyReportTotalEntity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:report:save")
    public R save(@RequestBody ProjectMonthlyReportEntity projectMonthlyReportEntity){
        projectMonthlyReportService.insert(projectMonthlyReportEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:report:update")
    public R update(@RequestBody ProjectMonthlyReportEntity projectMonthlyReportEntity){
        projectMonthlyReportService.updateAllColumnById(projectMonthlyReportEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:report:delete")
    public R delete(@RequestBody Long[] reportIds){
        Map<String, Object> params = new HashMap<>();
        for (Long reportId: reportIds) {
            params.put("reportId", reportId);
            projectMonthlyReportService.remove(params);
        }
        return R.ok();
    }
}
