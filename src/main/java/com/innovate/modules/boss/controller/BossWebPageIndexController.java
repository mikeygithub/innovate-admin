package com.innovate.modules.boss.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.innovate.common.annotation.SysLog;
import com.innovate.common.utils.R;
import com.innovate.common.validator.Assert;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.innovate.utils.SmsUtil;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.form.PasswordForm;
import com.innovate.modules.sys.service.SysUserRoleService;
import com.innovate.modules.sys.service.SysUserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private String VerificationCode;//短信验证码

    @RequestMapping("projectInfos")
    public R projectInfos(@RequestParam Map<String,Object> params){
        return  entProjectInfoService.queryWebEntProjectInfos(params);
    }

    @RequestMapping("newProjectInfos")
    public R newProjectInfos(@RequestParam Map<String,Object> params){
        return  entProjectInfoService.queryNewWebEntProjectInfos(params);
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

    /**
     * 短信发送
     */
    @PostMapping("/message")
    public R message(@RequestBody String mobile) throws Exception{
        SmsUtil sms = new SmsUtil();
        VerificationCode = sms.getFourRandom();
        JSONObject object = new JSONObject(mobile);
        String phone = object.getString("mobile");
        System.out.println(VerificationCode);
        sms.sendSms(phone,VerificationCode);
        return R.ok();
    }

    /**
     * 通过手机号码获取用户信息
     */
    @PostMapping("/mobile")
    public R mobile(@RequestBody String mobile) throws Exception{
        JSONObject object = new JSONObject(mobile);
        String phone = object.getString("mobile");
        List<SysUserEntity> users = sysUserService.queryByUserMobile(phone);
        String user = "";
        if(users.size()>0){
            for (int i = 0; i < users.size(); i++) {
                String user1 = users.get(i).getUsername();
                String user2 = user1 + " , ";
                user += user2;
            }
            user = user.substring(0, user.length()-2);
        }
        return R.ok().put("user", user);
    }

    @RequestMapping("update")
    public R updatePwd(@RequestParam( name = "tk", required = false) String tk,
                       @RequestParam( name = "pwd", required = false, defaultValue = "123456") String pwd,
                       @RequestParam( name = "ex", required = false) String ex,
                       @RequestParam( name = "type", required = false, defaultValue = "1")String type){
        // new Sha256Hash(user.getPassword(), salt).toHex()
        if(!"".equals(tk) && "1".equals(type)){// 单个更新密码
            SysUserEntity sysUserEntity = sysUserService.queryByUserName(tk);
            if(sysUserEntity != null){
                String newPwd = new Sha256Hash(pwd, sysUserEntity.getSalt()).toHex();
                boolean b = sysUserService.updatePassword(sysUserEntity.getUserId(), sysUserEntity.getPassword(), newPwd);
                return R.ok().put("statue", b);
            }
            return R.error("用户不存在");
        }else if("2".equals(type) && ex != null){ // 更新指定多个人
            String[] split = ex.split(",");
            EntityWrapper<SysUserEntity> wrapper = new EntityWrapper<>();
            wrapper.in("username",split);
            List<SysUserEntity> list = sysUserService.selectList(wrapper);
            HashMap<Object, Object> res = new HashMap<>();
            if(list != null){
                for(int i=0;i<list.size();i++){
                    SysUserEntity sysUserEntity = list.get(i);
                    String newPwd = new Sha256Hash(pwd, sysUserEntity.getSalt()).toHex();
                    boolean b = sysUserService.updatePassword(sysUserEntity.getUserId(), sysUserEntity.getPassword(), newPwd);
                    res.put(sysUserEntity.getUsername(), b);
                }
            }
            return R.ok().put("result", res);
        }else{// 更新全部
            EntityWrapper<SysUserEntity> wrapper = new EntityWrapper<>();
            if(ex != null){
                String[] split = ex.split(",");
                wrapper.notIn("username",split);
            }
            List<SysUserEntity> list = sysUserService.selectList(wrapper);
            HashMap<Object, Object> res = new HashMap<>();
            if(list != null){
                for(int i=0;i<list.size();i++){
                    SysUserEntity sysUserEntity = list.get(i);
                    if("SuperAdmin".equals(sysUserEntity.getUsername())){
                        continue;
                    }
                    String newPwd = new Sha256Hash(pwd, sysUserEntity.getSalt()).toHex();
                    boolean b = sysUserService.updatePassword(sysUserEntity.getUserId(), sysUserEntity.getPassword(), newPwd);
                    res.put(sysUserEntity.getUsername(), b);
                }
            }
            return R.ok().put("result", res);
        }
    }

    /**
     * 账号/密码找回
     */
    @SysLog("修改密码")
    @PostMapping("/password")
    public R password(@RequestBody Map<String, Object> params){
        Assert.isBlank(params.get("newPassword").toString(), "新密码不为能空");

        String captcha = params.get("captcha").toString();
        List<SysUserEntity> users = sysUserService.queryByUserMobile(params.get("mobile").toString());

        if (captcha.equals(VerificationCode)) {
            for (int i = 0; i < users.size(); i++) {
                //sha256加密
                String password = new Sha256Hash(params.get("password").toString(), users.get(i).getSalt()).toHex();
                //sha256加密
                String newPassword = new Sha256Hash(params.get("newPassword").toString(), users.get(i).getSalt()).toHex();
                //更新密码
                sysUserService.updatePassword(users.get(i).getUserId(), users.get(i).getPassword(), newPassword);
            }
        } else {
            return R.ok().put("come","0");
        }

        return R.ok().put("come","1");
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
