package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.Constant;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.DefaultArrayValue;
import com.innovate.modules.enterprise.annotation.DefaultValue;
import com.innovate.modules.enterprise.dao.EntProjectInfoDao;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import com.innovate.modules.enterprise.enums.DefValueEnum;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entProjectInfoService")
public class EntProjectInfoServiceImpl extends ServiceImpl<EntProjectInfoDao, EntProjectInfoEntity> implements EntProjectInfoService {

    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inType", defValue = "userPerId", defValueEnum = DefValueEnum.STRING)
    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply"}, defValue = {"0"}, defValueEnum = {DefValueEnum.STRING})
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<EntProjectInfoEntity> wrapper = new EntityWrapper<>();
        String type = (String) params.get("inType");
        if("userPerId".equals(type)){ // 学生
            wrapper.isNotNull("user_per_id");
        }else if("userTeacherId".equals(type)){ // 教师
            wrapper.isNotNull("user_teacher_id");
        }else if ("entInfoId".equals(type)){ // 企业
            wrapper.isNotNull("ent_info_id");
        }
        if("0".equals(params.get("inApply"))){
            wrapper.eq("in_apply", "0");
        }else {
            wrapper.eq("in_apply", "1");
        }
        if(params.get("key") != null){
            wrapper.like("pro_name", (String) params.get("key"));
        }
        Page<EntProjectInfoEntity> page = this.selectPage( new Query<EntProjectInfoEntity>(params).getPage(),wrapper);
        return new PageUtils(page);
    }

    @Override
    public R queryEntProjectInfoByIdPerId(Long id) {
        EntProjectInfoEntity entity = baseMapper.queryEntProjectInfoByIdPerId(id);
        return R.ok().put("data", entity);
    }

    @Override
    public R queryEntProjectInfoByIdTeacherId(Long id) {
        EntProjectInfoEntity entity = baseMapper.queryEntProjectInfoByIdTeacherId(id);
        return R.ok().put("data",entity);
    }

    @Override
    public R queryEntProjectInfoByIdEntId(Long id) {
        EntProjectInfoEntity entity = baseMapper.queryEntProjectInfoByIdEntId(id);
        return R.ok().put("data", entity);
    }

    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inApply", defValue = "1", defValueEnum = DefValueEnum.STRING)
    @Override
    public R updateEntExamine(Map<String, Object> params) {
        boolean b = baseMapper.updateEntExamine(params);
        return R.ok().put("data",b);
    }

}