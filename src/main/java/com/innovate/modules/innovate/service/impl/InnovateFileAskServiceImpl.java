package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.innovate.dao.InnovateFileAskDao;
import com.innovate.modules.innovate.entity.InnovateFileAskEntity;
import com.innovate.modules.innovate.service.InnovateFileAskService;


@Service("innovateFileAskService")
public class InnovateFileAskServiceImpl extends ServiceImpl<InnovateFileAskDao, InnovateFileAskEntity> implements InnovateFileAskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<InnovateFileAskEntity> page = this.selectPage(
//                new Query<InnovateFileAskEntity>(params).getPage(),
//                new EntityWrapper<InnovateFileAskEntity>()
//        );
//        return new PageUtils(page);
        Integer totalPage  = baseMapper.queryCountPage(params);
        Integer currPage  = 1;
        Integer pageSize  = 10;
        try {
            if (params.get("currPage")!=null&&params.get("pageSize")!=null) {
                currPage = Integer.parseInt(params.get("currPage").toString());
                pageSize = Integer.parseInt(params.get("pageSize").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer startPage = 0 + pageSize * (currPage - 1);
        Integer endPage = pageSize;

        params.put("startPage", startPage);
        params.put("endPage", endPage);
        List<InnovateFileAskEntity> fileAskEntityList = baseMapper.queryPage(params);

        return new PageUtils(fileAskEntityList, totalPage, pageSize, currPage);
    }

    @Override
    public InnovateFileAskEntity queryByParams(Map<String, Object> params) {
        return baseMapper.queryByParams(params);
    }

}