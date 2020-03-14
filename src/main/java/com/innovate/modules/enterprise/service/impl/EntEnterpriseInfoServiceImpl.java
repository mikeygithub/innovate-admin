package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.DefaultValue;
import com.innovate.modules.enterprise.dao.EntEnterpriseInfoDao;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.common.enums.DefValueEnum;
import com.innovate.modules.enterprise.service.EntEnterpriseInfoService;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.innovate.common.utils.ShiroUtils.getUserId;


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
        List<EntEnterpriseInfoEntity> records = page.getRecords();
        if(records != null && records.size() > 0){
            for(int i=0; i<records.size(); i++){
                EntEnterpriseInfoEntity entity = records.get(i);
                SysUserEntity userEntity = sysUserService.selectById(entity.getUserId());
                entity.setSysUser(userEntity);
            }
        }
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryListById(Map<String, Object> params) {
        Page<EntEnterpriseInfoEntity> page = this.selectPage(
                new Query<EntEnterpriseInfoEntity>(params).getPage(),
                new EntityWrapper<EntEnterpriseInfoEntity>().eq("user_id", getUserId())
        );
        return new PageUtils(page);
    }

    @Override
    //@ResultNotNull(clazz = EntEnterpriseInfoEntity.class, targetKey = "data", codeKey = "code", errorCode = 500)
    public R queryEntEnterpriseInfo(Long entInfoId, String inApply) {
        EntEnterpriseInfoEntity entity = entEnterpriseInfoDao.queryEntEnterpriseInfo(entInfoId, inApply);
        return R.ok().put("data", entity);
    }

    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inApply", defValue = "0", defValueEnum = DefValueEnum.STRING)
    @Override
    @Transactional
    public R updateEntExamine(Map params) {
        // 由于数据问题，此处可能会出现类型转换异常
        String inApply = (String) params.get("inApply");
        int status = 0;
        if("1".equals(inApply)){
            status = 1;
            boolean b = sysUserService.updateState((Long.valueOf((String)params.get("userId"))), status);
            boolean b1 = entEnterpriseInfoDao.updateInApply(Long.valueOf((String) params.get("entInfoId")), inApply);
            return b && b1 ? R.ok() : R.error();
        }
        // Integer.valueOf((String) params.get("status"))
        //sysUserService.deleteBatch(new Long[]{(Long.valueOf((String)params.get("userId")))});
        sysUserService.deleteById((Long.valueOf((String) params.get("userId"))));
        entEnterpriseInfoDao.deleteById(Long.valueOf((String) params.get("entInfoId")));
        return R.ok();
    }

    @Override
    public Long queryEntInfoIdByUserId(Long userId) {
        return entEnterpriseInfoDao.queryEntInfoIdByUserId(userId);
    }

    @Override
    public List<Long> queryUserIdByEntInfoId(Long[] ids) {
        return entEnterpriseInfoDao.queryUserIdByEntInfoId(ids);
    }

}