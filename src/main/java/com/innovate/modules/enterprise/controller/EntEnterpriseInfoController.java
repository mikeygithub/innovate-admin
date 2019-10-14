package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.HasRole;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.service.EntEnterpriseInfoService;
import com.innovate.modules.enterprise.service.EntRecruitmentInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import com.innovate.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 企业基本信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@RestController
@RequestMapping("enterprise/info")
public class EntEnterpriseInfoController extends AbstractController {
    @Autowired
    private EntEnterpriseInfoService entEnterpriseInfoService;

    @Autowired
    private EntRecruitmentInfoService entRecruitmentInfoService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("enterprise:info:list")
    @HasRole(name = "企业入驻审核", roles = {"8", "9"})
    public R list(@RequestParam Map<String, Object> params){
        // parmas ==> 传递一个数企业类型
        String newHighZones = (String) params.get("_queryType");
        if(newHighZones.equals("8")){
            params.put("new_high_zones", "1");
        }else{
            params.put("new_high_zones", "0");
        }
        PageUtils page = entEnterpriseInfoService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息---企业入驻审核信息查询
     */
    @RequestMapping("/info/{entInfoId}/{inApply}")
    // @RequiresPermissions("enterprise:info")
    public R info(@PathVariable("entInfoId") Long entInfoId, @PathVariable("inApply")String inApply){
		return entEnterpriseInfoService.queryEntEnterpriseInfo(entInfoId, inApply);
    }

    /**
     * 处理企业入驻审核
     * @return
     */
    @RequestMapping("/entExamine")
    public R entExamine(@RequestParam  Map<String, Object> params){
        logger.info("接收数据:{}", params);
        return entEnterpriseInfoService.updateEntExamine(params);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:info:save")
    public R save(@RequestBody EntEnterpriseInfoEntity entEnterpriseInfo){
		entEnterpriseInfoService.insert(entEnterpriseInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:info:update")
    public R update(@RequestBody EntEnterpriseInfoEntity entEnterpriseInfo){
		entEnterpriseInfoService.updateById(entEnterpriseInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:info:delete")
    @Transactional
    public R delete(@RequestBody Long[] entInfoIds){
        List<Long> userIds = entEnterpriseInfoService.queryUserIdByEntInfoId(entInfoIds);
        entEnterpriseInfoService.deleteBatchIds(Arrays.asList(entInfoIds));
        entRecruitmentInfoService.deleteBatchIds(Arrays.asList(entInfoIds));
        sysUserService.deleteBatchIds(userIds);
        return R.ok();
    }

}
