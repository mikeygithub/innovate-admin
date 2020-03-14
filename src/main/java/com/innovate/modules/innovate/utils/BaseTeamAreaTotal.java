package com.innovate.modules.innovate.utils;

import com.innovate.modules.innovate.service.BaseProjectStationService;
import com.innovate.modules.innovate.service.ProjectInfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author:tz
 * @create:2018-11-22
 * @description: 企业使用面积
 **/
@Component
@Slf4j
public class BaseTeamAreaTotal {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static BaseTeamAreaTotal total;

    @Autowired
    private ProjectInfoService innovateProjectInfoService;
    @Autowired
    private BaseProjectStationService stationService;

    //通过init方法，
    //1.注入bean(innovateProjectInfoService)
    //2.赋值给static BaseTeamAreaTotal total
    //3.innovateProjectInfoService，就通过total来取
    @PostConstruct
    public void init(){
        total = this;
        total.innovateProjectInfoService = this.innovateProjectInfoService;
        total.stationService = this.stationService;
    }

    public static Double calculate(Long baseId){
        //企业使用面积
        Double baseTeamArea = 0.0;
        //查项目中有工位的工位id
        List<Long> stationIdList = total.innovateProjectInfoService.queryStationIdList(1L);
        log.info("tz----工位----->"+stationIdList);
        //筛选出满足条件的
        for (int i = stationIdList.size()-1; i >= 0; i--){
            if (baseId != total.stationService.queryBaseId(stationIdList.get(i))){
                stationIdList.remove(i);
            }
        }
        //通过工位id查工位面积
        for (Long id:stationIdList){
            Double baseArea = total.stationService.queryArea(id);
            baseTeamArea += baseArea;
        }
        return baseTeamArea;
    }
}
