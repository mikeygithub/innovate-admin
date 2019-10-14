package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 项目信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@RestController
@RequestMapping("enterprise/project/info")
public class EntProjectInfoController extends AbstractController {
    @Autowired
    private EntProjectInfoService entProjectInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("enterprise:project:info:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entProjectInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{hasType}/{proInfoId}")
    //@RequiresPermissions("enterprise:project:info")
    public R info(@PathVariable("proInfoId") Long proInfoId, @PathVariable("hasType") String hasType){
        if("userPerId".equals(hasType)){ // 学生
            return entProjectInfoService.queryEntProjectInfoByIdPerId(proInfoId);
        }else if("userTeacherId".equals(hasType)){ // 教师
            return entProjectInfoService.queryEntProjectInfoByIdTeacherId(proInfoId);
        }else if ("entInfoId".equals(hasType)){ // 企业
            return entProjectInfoService.queryEntProjectInfoByIdEntId(proInfoId);
        }
        return R.ok();
    }

    /**
     * 处理项目信息审核
     * @return
     */
    @RequestMapping("/entExamine")
    public R entExamine(@RequestParam  Map<String, Object> params){
        logger.info("接收数据:{}", params);
        return entProjectInfoService.updateEntExamine(params);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:project:info:save")
    public R save(@RequestBody EntProjectInfoEntity entProjectInfo){
		entProjectInfoService.insert(entProjectInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:project:info:update")
    public R update(@RequestBody EntProjectInfoEntity entProjectInfo){
		entProjectInfoService.updateById(entProjectInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:project:info:delete")
    public R delete(@RequestBody Long[] proInfoIds){
		entProjectInfoService.deleteBatchIds(Arrays.asList(proInfoIds));

        return R.ok();
    }

}
