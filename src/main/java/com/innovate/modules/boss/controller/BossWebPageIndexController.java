package com.innovate.modules.boss.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.service.SysUserRoleService;
import com.innovate.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserTeacherInfoService userTeacherInfoService;

    @Autowired
    private UserPerInfoService userPerInfoService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

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

//    @RequestMapping("per")
//    public R templatePer(){
//        //List<SysUserEntity> list = sysUserService.selectList(new EntityWrapper<SysUserEntity>());
//        List<UserPersonInfoEntity> list = userPerInfoService.selectList(new EntityWrapper<UserPersonInfoEntity>());
//        List<HashMap<Object,Object>> result = new ArrayList<HashMap<Object,Object>>();
//        if(list != null && list.size() > 0){
//            for (int i = 0; i < list.size(); i++) {
//                HashMap<Object, Object> map = new HashMap<>();
//                UserPersonInfoEntity entity = list.get(i);
//                map.put("学生",entity.getUserId());
//                List<Long> longs = sysUserRoleService.queryRoleIdList(entity.getUserId());
//                if(longs != null){
//                    boolean flag = false;
//                    for (int j = 0; j < longs.size(); j++){
//                        if(longs.get(j).longValue() == 11L){
//                            flag = true;
//                            map.put("角色","有学生角色");
//                        }
//                    }
//                    if(!flag){
//                        longs.add(11L);
//                        sysUserRoleService.saveOrUpdate(entity.getUserId(),longs);
//                        map.put("是否新增成功", "是");
//                    }else{
//                        map.put("是否新增成功", "否");
//                    }
//                }
//                result.add(map);
//            }
//        }
//        return R.ok().put("data", result);
//    }
//
//    @RequestMapping("tea")
//    public R templateTea(){
//        List<UserTeacherInfoEntity> list = userTeacherInfoService.selectList(new EntityWrapper<UserTeacherInfoEntity>());
//        List<HashMap<Object,Object>> result = new ArrayList<HashMap<Object,Object>>();
//        if(list != null && list.size() > 0){
//            for (int i = 0; i < list.size(); i++) {
//                HashMap<Object, Object> map = new HashMap<>();
//                UserTeacherInfoEntity entity = list.get(i);
//                map.put("教师",entity.getUserId());
//                List<Long> longs = sysUserRoleService.queryRoleIdList(entity.getUserId());
//                if(longs != null){
//                    boolean flag = false;
//                    for (int j = 0; j < longs.size(); j++){
//                        if(longs.get(j).longValue() == 12L){
//                            flag = true;
//                            map.put("角色","有教师角色");
//                        }
//                    }
//                    if(!flag){
//                        longs.add(12L);
//                        sysUserRoleService.saveOrUpdate(entity.getUserId(),longs);
//                        map.put("是否新增成功", "是");
//                    }else{
//                        map.put("是否新增成功", "否");
//                    }
//                }
//                result.add(map);
//            }
//        }
//        return R.ok().put("data", result);
//    }

}
