package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.match.dao.MatchEventDao;
import com.innovate.modules.match.entity.MatchEventEntity;
import com.innovate.modules.match.service.MatchAwardService;
import com.innovate.modules.match.service.MatchEventService;
import com.innovate.modules.match.service.MatchInfoService;
import com.innovate.modules.match.service.MatchStaffInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Service
public class MatchEventServiceImpl extends ServiceImpl<MatchEventDao, MatchEventEntity> implements MatchEventService {

    @Autowired
    private MatchEventService matchEventService;
    @Autowired
    private MatchAwardService matchAwardService;
    @Autowired
    private MatchStaffInfoService matchStaffInfoService;
    @Autowired
    private MatchInfoService matchInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = params.get("key").toString();

        EntityWrapper<MatchEventEntity> ew = new EntityWrapper<>();

        ew.setEntity(new MatchEventEntity());

        if (key!=null&&!key.equals(""))ew.like("event_name",key, SqlLike.DEFAULT);

        Page<MatchEventEntity> page = this.selectPage(
                new Query<MatchEventEntity>(params).getPage(),
                ew
        );
        return new PageUtils(page);
    }

    @Override
    public List<MatchEventEntity> queryAllEvent() {
        return baseMapper.queryAllEvent();
    }

    @Override
    public void total(Map<String, Object> params) {
        MatchEventEntity matchEventEntity = matchEventService.queryByEventId(params);
        //统计奖金数量
        Double awardMoney = matchAwardService.queryAwardMoney(params);
        //统计获奖数量
        Long awardNum = matchAwardService.queryAwardNum(params);
        //统计项目个数
        Long projectNum = matchInfoService.queryProjectNum(params);
        //统计负责人个数
        Long userNum = matchInfoService.queryUserNum(params);
        //统计参与者个数
        Long staffNum = matchStaffInfoService.queryUserNum(params);

        matchEventEntity.setEventAwardMoney(awardMoney);
        matchEventEntity.setEventProjectNum(projectNum);
        matchEventEntity.setEventAwardNum(awardNum);
        matchEventEntity.setEventPeople((userNum+staffNum));
        matchEventService.insertOrUpdate(matchEventEntity);
    }

    @Override
    public MatchEventEntity queryByEventId(Map<String, Object> params) {
        return baseMapper.queryByEventId(params);
    }
}
