package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.PagingTool;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.DefaultValue;
import com.innovate.modules.enterprise.dao.EntRecruitmentInfoDao;
import com.innovate.modules.enterprise.entity.EntRecruitmentInfoEntity;
import com.innovate.modules.enterprise.enums.DefValueEnum;
import com.innovate.modules.enterprise.service.EntRecruitmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("entRecruitmentInfoService")
public class EntRecruitmentInfoServiceImpl extends ServiceImpl<EntRecruitmentInfoDao, EntRecruitmentInfoEntity> implements EntRecruitmentInfoService {

    @Autowired
    private EntRecruitmentInfoDao entRecruitmentInfoDao;

    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inApply", defValue = "0", defValueEnum = DefValueEnum.STRING)
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<EntRecruitmentInfoEntity> page = this.selectPage(
//                new Query<EntRecruitmentInfoEntity>(params).getPage(),
//                new EntityWrapper<EntRecruitmentInfoEntity>()
//        );
        Page<?> objects = PagingTool.handlerPage(params);
//        if(null == params.get("inApply")){
//            params.put("inApply", "0");
//        }
        List<EntRecruitmentInfoEntity> list = entRecruitmentInfoDao.entRecruitmentInfoList(params);
        PageUtils page = PagingTool.page(list, objects);
        return page;
    }

    @Override
    public R entRecruitmentInfoById(Long recruitmentInfoId) {
        EntRecruitmentInfoEntity entity = entRecruitmentInfoDao.entRecruitmentInfoById(recruitmentInfoId);
        return R.ok().put(PagingTool.DATA_KEY, entity);
    }

}