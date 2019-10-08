package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.ResultNotNull;
import com.innovate.modules.enterprise.dao.EntEnterpriseInfoDao;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.service.EntEnterpriseInfoService;
import com.innovate.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("entEnterpriseInfoService")
public class EntEnterpriseInfoServiceImpl extends ServiceImpl<EntEnterpriseInfoDao, EntEnterpriseInfoEntity> implements EntEnterpriseInfoService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private EntEnterpriseInfoDao entEnterpriseInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        if(params.get("inApply") == null){
            params.put("inApply", "0");
        }
        Page<EntEnterpriseInfoEntity> page = this.selectPage(
                new Query<EntEnterpriseInfoEntity>(params).getPage(),
                new EntityWrapper<EntEnterpriseInfoEntity>().eq("new_high_zones", params.get("new_high_zones")).eq("in_apply", params.get("inApply"))
        );

        return new PageUtils(page);
    }

    @Override
    //@ResultNotNull(clazz = EntEnterpriseInfoEntity.class, targetKey = "data", codeKey = "code", errorCode = 500)
    public R queryEntEnterpriseInfo(Long entInfoId, String inApply) {
        EntEnterpriseInfoEntity entity = entEnterpriseInfoDao.queryEntEnterpriseInfo(entInfoId, inApply);
        return R.ok().put("data", entity);
    }

    @Override
    @Transactional
    public R updateEntExamine(Map params) {
        // 由于数据问题，此处可能会出现类型转换异常
        String inApply = (String) params.get("inApply");
        int status = 0;
        if("1".equals(inApply)){
            status = 1;
        }
        // Integer.valueOf((String) params.get("status"))
        boolean b = sysUserService.updateState((Long.valueOf((String)params.get("userId"))), status);
        boolean b1 = entEnterpriseInfoDao.updateInApply(Long.valueOf((String) params.get("entInfoId")), inApply);
        return b && b1 ? R.ok() : R.error();
    }

}