package com.innovate.modules.finish.service.impl;

import com.innovate.common.utils.PageUtils;
import com.innovate.modules.finish.entity.*;
import com.innovate.modules.finish.service.*;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 **/
@Service
public class FinishInfoModelServiceImpl implements FinishInfoModelService {
    @Autowired
    private FinishAttachService finishAttachService;
    @Autowired
    private UserPerInfoService userPerInfoService;
    @Autowired
    private FinishStaffInfoService finishStaffInfoService;
    @Autowired
    private FinishInfoService finishInfoService;
    @Autowired
    private FinishTeacherService finishTeacherService;
    @Autowired
    private UserTeacherInfoService userTeacherInfoService;
    @Autowired
    private FinishReviewService finishReviewService;

    private List<FinishTeacherEntity> finishTeacherEntities;
    private List<UserTeacherInfoEntity> userTeacherInfoEntities;
    private List<UserPersonInfoEntity> userPersonInfoEntities;
    private List<FinishAttachEntity> finishAttachEntities;
    private List<FinishStaffInfoEntity> finishStaffInfoEntities;
    private List<FinishReviewEntity> finishReviewEntities;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Integer totalPage  = finishInfoService.queryCountPage(params);
        Integer currPage  = 1;
        Integer pageSize  = 10;
        try {
            if (params.get("currPage")!=null&&params.get("currPage")!="")currPage  = Integer.parseInt(params.get("currPage").toString());
            if (params.get("pageSize")!=null&&params.get("pageSize")!="")pageSize  = Integer.parseInt(params.get("pageSize").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer startPage = 0 + pageSize * (currPage - 1);
        Integer endPage = pageSize;

        params.put("startPage", startPage);
        params.put("endPage", endPage);
//        临时接收项目信息
        List<FinishInfoEntity> tempLists = null;
//        项目所有信息的临时实体
        FinishInfoModel tempFinishInfoModel = null;
//        包含项目所有信息的主要实体
        List<FinishInfoModel> finishInfoModels = new ArrayList<>();

        tempLists = finishInfoService.queryPage(params);

        Map<String, Object> tempParams = new HashMap<>();

        for (FinishInfoEntity finishInfoEntity : tempLists) {
            tempParams.put("finishId", finishInfoEntity.getFinishId());
            tempFinishInfoModel = this.query(tempParams);
            finishInfoModels.add(tempFinishInfoModel);
        }
        return new PageUtils(finishInfoModels, totalPage, pageSize, currPage);
    }

    @Override
    public List<FinishInfoModel> queryAll(Map<String, Object> params) {
        return null;
    }

    @Override
    public FinishInfoModel query(Map<String, Object> params) {

        Long finishId = Long.parseLong(params.get("finishId").toString());
        FinishInfoModel tempFinishInfoModel = new FinishInfoModel();

//        根据项目ID查询出对应的项目信息
        FinishInfoEntity finishInfoEntity = finishInfoService.selectById(finishId);
        tempFinishInfoModel.setFinishInfoEntity(finishInfoEntity);

//        获取项目相关的所有表的数据信息
        userPersonInfoEntities = userPerInfoService.queryAllPersonInfo(params);
        finishTeacherEntities = finishTeacherService.queryAll(params);
        userTeacherInfoEntities = userTeacherInfoService.queryFinishTeacherInfo(finishId);
        finishAttachEntities = finishAttachService.queryAll(params);
        finishStaffInfoEntities = finishStaffInfoService.queryAll(params);
        finishReviewEntities = finishReviewService.queryAll(params);

//        将项目相关的信息放入tempInnovatefinishInfoModel中
        tempFinishInfoModel.setUserPersonInfoEntities(userPersonInfoEntities);
        tempFinishInfoModel.setFinishTeacherEntities(finishTeacherEntities);
        tempFinishInfoModel.setFinishUserTeacherInfoEntities(userTeacherInfoEntities);
        tempFinishInfoModel.setFinishAttachEntities(finishAttachEntities);
        tempFinishInfoModel.setFinishStaffInfoEntities(finishStaffInfoEntities);
        tempFinishInfoModel.setFinishReviewEntities(finishReviewEntities);

        return tempFinishInfoModel;
    }

    @Override
    public void saveEntity(FinishInfoModel finishInfoModel) {
        finishInfoService.insert(finishInfoModel.getFinishInfoEntity());
        this.saveOrupdateProps(finishInfoModel);
    }

    @Override
    public void updateEntity(FinishInfoModel finishInfoModel) {
        finishInfoService.updateById(finishInfoModel.getFinishInfoEntity());
        this.saveOrupdateProps(finishInfoModel);
    }

    @Override
    public void saveOrupdateProps(FinishInfoModel finishInfoModel) {

        Long finishId = finishInfoModel.getFinishInfoEntity().getFinishId();

        //指导老师
        for (FinishTeacherEntity finishTeacherEntity : finishInfoModel.getFinishTeacherEntities()) {
            //根据最新时间确定第一第二指导老师
            finishTeacherEntity.setCreateTime(new Date());
            finishTeacherEntity.setFinishId(finishId);
            finishTeacherService.insertOrUpdate(finishTeacherEntity);
        }
        for (FinishAttachEntity finishAttachEntity : finishInfoModel.getFinishAttachEntities()) {
            finishAttachEntity.setFinishId(finishId);
            finishAttachService.insertOrUpdate(finishAttachEntity);
        }
        for (FinishStaffInfoEntity finishStaffInfoEntity : finishInfoModel.getFinishStaffInfoEntities()) {
            finishStaffInfoEntity.setFinishId(finishId);
            finishStaffInfoService.insertOrUpdate(finishStaffInfoEntity);
        }
    }

    @Override
    public void deleteEntity(Map<String, Object> params) {
        this.deleteProps(params);
        finishInfoService.remove(params);
    }

    @Override
    public void deleteProps(Map<String, Object> params) {
        finishTeacherService.remove(params);
        finishAttachService.remove(params);
        finishStaffInfoService.remove(params);
    }
}
