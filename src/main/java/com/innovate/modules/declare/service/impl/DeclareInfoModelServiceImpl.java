package com.innovate.modules.declare.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.declare.service.*;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.declare.entity.*;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 **/
@Service
public class DeclareInfoModelServiceImpl implements DeclareInfoModelService {

    private Logger logger = Logger.getLogger(DeclareInfoModelServiceImpl.class);

    @Autowired
    private DeclareAttachService declareAttachService;
    @Autowired
    private UserPerInfoService userPerInfoService;
    @Autowired
    private DeclareStaffInfoService declareStaffInfoService;
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private DeclareTeacherService declareTeacherService;
    @Autowired
    private UserTeacherInfoService userTeacherInfoService;
    @Autowired
    private DeclareReviewService declareReviewService;
    @Autowired
    private DeclareAwardService declareAwardService;

    private List<DeclareTeacherEntity> declareTeacherEntities;
    private List<UserTeacherInfoEntity> userTeacherInfoEntities;
    private List<UserPersonInfoEntity> userPersonInfoEntities;
    private List<DeclareAttachEntity> declareAttachEntities;
    private List<DeclareStaffInfoEntity> declareStaffInfoEntities;
    private List<DeclareReviewEntity> declareReviewEntities;
    private List<DeclareAwardEntity> declareAwardEntities;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Integer totalPage  = declareInfoService.queryCountPage(params);
        Integer currPage  = 1;
        Integer pageSize  = 10;
        try {
            if (params.get("currPage")!=null&&params.get("pageSize")!=null) {
                pageSize = Integer.parseInt(params.get("pageSize").toString());
                currPage = Integer.parseInt(params.get("currPage").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer startPage = 0 + pageSize * (currPage - 1);
        Integer endPage = pageSize;

        params.put("startPage", startPage);
        params.put("endPage", endPage);
//        临时接收项目信息
        List<DeclareInfoEntity> tempLists = null;
//        项目所有信息的临时实体
        DeclareInfoModel tempdeclareInfoModel = null;
//        包含项目所有信息的主要实体
        List<DeclareInfoModel> declareInfoModels = new ArrayList<>();
        tempLists = declareInfoService.queryPage(params);
        Map<String, Object> tempParams = new HashMap<>();

        for (DeclareInfoEntity declareInfoEntity : tempLists) {
            tempParams.put("declareId", declareInfoEntity.getDeclareId());
            tempdeclareInfoModel = this.query(tempParams);
            declareInfoModels.add(tempdeclareInfoModel);
        }

        return new PageUtils(declareInfoModels, totalPage, pageSize, currPage);
    }

    /**
     * 不分页查询
     * @param params
     * @return
     */
    @Override
    public List<DeclareInfoModel> queryAll(Map<String, Object> params) {

//        项目所有信息的临时实体
        DeclareInfoModel tempdeclareInfoModel = null;
//        临时接收项目信息
        List<DeclareInfoEntity> tempLists = null;
//        包含项目所有信息的主要实体
        List<DeclareInfoModel> declareInfoModels = new ArrayList<>();

        tempLists = declareInfoService.queryPage(params);

        Map<String, Object> tempParams = new HashMap<>();

        for (DeclareInfoEntity declareInfoEntity : tempLists) {
            tempParams.put("declareId", declareInfoEntity.getDeclareId());
            tempdeclareInfoModel = this.query(tempParams);
            declareInfoModels.add(tempdeclareInfoModel);
        }

        return declareInfoModels;
    }

    @Override
    public DeclareInfoModel query(Map<String, Object> params) {

        Long declareId = Long.parseLong(params.get("declareId").toString());
        DeclareInfoModel tempdeclareInfoModel = new DeclareInfoModel();

//        根据项目ID查询出对应的项目信息
        DeclareInfoEntity declareInfoEntity = declareInfoService.selectById(declareId);
        tempdeclareInfoModel.setDeclareInfoEntity(declareInfoEntity);

//        获取项目相关的所有表的数据信息
        userPersonInfoEntities = userPerInfoService.queryAllPersonInfo(params);
        declareTeacherEntities = declareTeacherService.queryAll(params);
        userTeacherInfoEntities = userTeacherInfoService.queryDeclareTeacherInfo(declareId);
        declareAttachEntities = declareAttachService.queryAll(params);
        declareStaffInfoEntities = declareStaffInfoService.queryAll(params);
        declareReviewEntities = declareReviewService.queryAll(params);
        declareAwardEntities = declareAwardService.queryAll(params);

//        将项目相关的信息放入tempInnovatedeclareInfoModel中
        tempdeclareInfoModel.setUserPersonInfoEntities(userPersonInfoEntities);
        tempdeclareInfoModel.setDeclareTeacherEntities(declareTeacherEntities);
        tempdeclareInfoModel.setDeclareUserTeacherInfoEntities(userTeacherInfoEntities);
        tempdeclareInfoModel.setDeclareAttachEntities(declareAttachEntities);
        tempdeclareInfoModel.setDeclareStaffInfoEntities(declareStaffInfoEntities);
        tempdeclareInfoModel.setDeclareReviewEntities(declareReviewEntities);
        tempdeclareInfoModel.setDeclareAwardEntities(declareAwardEntities);

        return tempdeclareInfoModel;
    }

    @Override
    public void saveEntity(DeclareInfoModel declareInfoModel) {

        //D+当前时间戳自动生成项目编号
        declareInfoModel.getDeclareInfoEntity().setDeclareNum("D"+System.currentTimeMillis());

        declareInfoService.insert(declareInfoModel.getDeclareInfoEntity());
        this.saveOrupdateProps(declareInfoModel);
    }

    @Override
    public void updateEntity(DeclareInfoModel declareInfoModel) {
        declareInfoService.updateById(declareInfoModel.getDeclareInfoEntity());
        this.saveOrupdateProps(declareInfoModel);
    }

    @Override
    public void saveOrupdateProps(DeclareInfoModel declareInfoModel) {

        Long declareId = declareInfoModel.getDeclareInfoEntity().getDeclareId();

        for (DeclareTeacherEntity declareTeacherEntity : declareInfoModel.getDeclareTeacherEntities()) {
            declareTeacherEntity.setDeclareId(declareId);
            declareTeacherService.insertOrUpdate(declareTeacherEntity);
        }
        //移除文件
        Map<String,Object> params = new HashMap<>();
        params.put("declareId", declareId);
        declareAttachService.remove(params);
        //保存文件
        for (DeclareAttachEntity declareAttachEntity : declareInfoModel.getDeclareAttachEntities()) {
            declareAttachEntity.setDeclareId(declareId);
            declareAttachService.insertOrUpdate(declareAttachEntity);
        }
        for (DeclareStaffInfoEntity declareStaffInfoEntity : declareInfoModel.getDeclareStaffInfoEntities()) {
            declareStaffInfoEntity.setDeclareId(declareId);
            declareStaffInfoService.insertOrUpdate(declareStaffInfoEntity);
        }
        for (DeclareAwardEntity declareAwardEntity : declareInfoModel.getDeclareAwardEntities()) {
            declareAwardEntity.setDeclareId(declareId);
            declareAwardService.insertOrUpdate(declareAwardEntity);
        }
    }

    @Override
    public void deleteEntity(Map<String, Object> params) {
        this.deleteProps(params);
        declareInfoService.remove(params);
    }

    @Override
    public void deleteProps(Map<String, Object> params) {
        declareTeacherService.remove(params);
        declareAttachService.remove(params);
        declareStaffInfoService.remove(params);
        declareAwardService.remove(params);
    }

    /**
     * 查询可结题的项目
     * @param params
     * @return
     */
    @Override
    public List<DeclareInfoEntity> queryCanFinish(Map<String, Object> params) {

        EntityWrapper entityWrapper = new EntityWrapper();

        //用户id
        Object userId = params.get("userId");
        entityWrapper.eq("project_user_id",userId);
        //评分通过
        Object status = params.get("status");
        entityWrapper.ge("project_audit_apply_status",status);

        //未结题
        entityWrapper.eq("finish_status",0);
        //未删除
        entityWrapper.eq("is_del",0);


    return   declareInfoService.selectList(entityWrapper);

    }

}
