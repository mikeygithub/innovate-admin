package com.innovate.modules.match.service.impl;

import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.*;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.match.entity.*;
import com.innovate.modules.match.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 **/
@Service
public class MatchInfoModelServiceImpl implements MatchInfoModelService {
    @Autowired
    private MatchAttachService matchAttachService;
    @Autowired
    private MatchAwardService matchAwardService;
    @Autowired
    private UserPerInfoService userPerInfoService;
    @Autowired
    private MatchStaffInfoService matchStaffInfoService;
    @Autowired
    private MatchInfoService matchInfoService;
    @Autowired
    private MatchTeacherService matchTeacherService;
    @Autowired
    private MatchReviewService matchReviewService;
    @Autowired
    private UserTeacherInfoService userTeacherInfoService;

    private List<MatchTeacherEntity> matchTeacherEntities;
    private List<UserPersonInfoEntity> userPersonInfoEntities;
    private List<MatchAttachEntity> matchAttachEntities;
    private List<MatchStaffInfoEntity> matchStaffInfoEntities;
    private List<MatchAwardEntity> matchAwardEntities;
    private List<MatchReviewEntity> matchReviewEntities;
    private List<UserTeacherInfoEntity> userTeacherInfoEntities;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Integer totalPage  = matchInfoService.queryCountPage(params);
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
//        临时接收项目信息
        List<MatchInfoEntity> tempLists = null;
//        项目所有信息的临时实体
        MatchInfoModel tempMatchInfoModel = null;
//        包含项目所有信息的主要实体
        List<MatchInfoModel> matchInfoModels = new ArrayList<>();
        tempLists = matchInfoService.queryPage(params);
        Map<String, Object> tempParams = new HashMap<>();

        for (MatchInfoEntity matchInfoEntity : tempLists) {
            tempParams.put("matchId", matchInfoEntity.getMatchId());
            tempMatchInfoModel = this.query(tempParams);
            matchInfoModels.add(tempMatchInfoModel);
        }
        return new PageUtils(matchInfoModels, totalPage, pageSize, currPage);
    }

    @Override
    public List<MatchInfoModel> queryAll(Map<String, Object> params) {
        return null;
    }

    @Override
    public MatchInfoModel query(Map<String, Object> params) {

        Long matchId = Long.parseLong(params.get("matchId").toString());
        MatchInfoModel tempMatchInfoModel = new MatchInfoModel();

//        根据项目ID查询出对应的项目信息
        MatchInfoEntity matchInfoEntity = matchInfoService.selectById(matchId);
        tempMatchInfoModel.setMatchInfoEntity(matchInfoEntity);

//        获取项目相关的所有表的数据信息
        userPersonInfoEntities = userPerInfoService.queryAllPersonInfo(params);
        matchTeacherEntities = matchTeacherService.queryAll(params);
        userTeacherInfoEntities = userTeacherInfoService.queryMatchTeacherInfo(matchId);
        matchAttachEntities = matchAttachService.queryAll(params);
        matchStaffInfoEntities = matchStaffInfoService.queryAll(params);
        matchAwardEntities = matchAwardService.queryAll(params);
        matchReviewEntities = matchReviewService.queryAll(params);

//        将项目相关的信息放入tempInnovatematchInfoModel中
        tempMatchInfoModel.setUserPersonInfoEntities(userPersonInfoEntities);
        tempMatchInfoModel.setMatchTeacherEntities(matchTeacherEntities);
        tempMatchInfoModel.setUserTeacherInfoEntities(userTeacherInfoEntities);
        tempMatchInfoModel.setMatchAttachEntities(matchAttachEntities);
        tempMatchInfoModel.setMatchStaffInfoEntities(matchStaffInfoEntities);
        tempMatchInfoModel.setMatchAwardEntities(matchAwardEntities);
        tempMatchInfoModel.setMatchReviewEntities(matchReviewEntities);

        return tempMatchInfoModel;
    }

    @Override
    public void saveEntity(MatchInfoModel matchInfoModel) {
        matchInfoService.insert(matchInfoModel.getMatchInfoEntity());
        this.saveOrupdateProps(matchInfoModel);
    }

    @Override
    public void saveAwardEntity(MatchInfoModel matchInfoModel) {
        Long matchId = matchInfoModel.getMatchInfoEntity().getMatchId();

        for (MatchAwardEntity matchAwardEntity : matchInfoModel.getMatchAwardEntities()) {
            matchAwardEntity.setMatchId(matchId);
            matchAwardService.insertOrUpdate(matchAwardEntity);
        }
    }

    @Override
    public void updateEntity(MatchInfoModel matchInfoModel) {
        matchInfoService.updateById(matchInfoModel.getMatchInfoEntity());
        this.saveOrupdateProps(matchInfoModel);
    }

    @Override
    public void saveOrupdateProps(MatchInfoModel matchInfoModel) {

        Long matchId = matchInfoModel.getMatchInfoEntity().getMatchId();

        for (MatchTeacherEntity matchTeacherEntity : matchInfoModel.getMatchTeacherEntities()) {
            matchTeacherEntity.setMatchId(matchId);
            matchTeacherService.insertOrUpdate(matchTeacherEntity);
        }
        //移除文件
        Map<String,Object> params = new HashMap<>();
        params.put("matchId", matchInfoModel.getMatchInfoEntity().getMatchId());
        matchAttachService.remove(params);
        //保存文件
        for (MatchAttachEntity matchAttachEntity : matchInfoModel.getMatchAttachEntities()) {
            matchAttachEntity.setMatchId(matchId);
            matchAttachService.insertOrUpdate(matchAttachEntity);
        }
        for (MatchStaffInfoEntity matchStaffInfoEntity : matchInfoModel.getMatchStaffInfoEntities()) {
            matchStaffInfoEntity.setMatchId(matchId);
            matchStaffInfoService.insertOrUpdate(matchStaffInfoEntity);
        }
    }

    @Override
    public void deleteEntity(Map<String, Object> params) {
        this.deleteProps(params);
        matchInfoService.remove(params);
    }

    @Override
    public void deleteProps(Map<String, Object> params) {
        matchTeacherService.remove(params);
        matchAttachService.remove(params);
        matchStaffInfoService.remove(params);
        matchAwardService.remove(params);
    }
}
