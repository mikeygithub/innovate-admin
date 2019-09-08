package com.innovate.modules.declare.service.impl;

import com.innovate.common.utils.PageUtils;
import com.innovate.modules.declare.service.*;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.declare.entity.*;
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
public class DeclareInfoModelServiceImpl implements DeclareInfoModelService {
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
    private DeclareReviewService declareReviewService;
    @Autowired
    private DeclareAwardService declareAwardService;

    private List<DeclareTeacherEntity> declareTeacherEntities;
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

    @Override
    public List<DeclareInfoModel> queryAll(Map<String, Object> params) {
        return null;
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
        declareAttachEntities = declareAttachService.queryAll(params);
        declareStaffInfoEntities = declareStaffInfoService.queryAll(params);
        declareReviewEntities = declareReviewService.queryAll(params);
        declareAwardEntities = declareAwardService.queryAll(params);

//        将项目相关的信息放入tempInnovatedeclareInfoModel中
        tempdeclareInfoModel.setUserPersonInfoEntities(userPersonInfoEntities);
        tempdeclareInfoModel.setDeclareTeacherEntities(declareTeacherEntities);
        tempdeclareInfoModel.setDeclareAttachEntities(declareAttachEntities);
        tempdeclareInfoModel.setDeclareStaffInfoEntities(declareStaffInfoEntities);
        tempdeclareInfoModel.setDeclareReviewEntities(declareReviewEntities);
        tempdeclareInfoModel.setDeclareAwardEntities(declareAwardEntities);

        return tempdeclareInfoModel;
    }

    @Override
    public void saveEntity(DeclareInfoModel declareInfoModel) {
//        declareInfoModel.
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

    @Override
    public List<DeclareInfoModel> queryErCollect(Map<String, Object> params) {
        Long instituteId = Long.parseLong(params.get("instituteId").toString());

        List<DeclareInfoModel> total=new CopyOnWriteArrayList<DeclareInfoModel>();

        //根据学院查询出用户
        List<UserPersonInfoEntity> userPersonInfoEntities = userPerInfoService.queryByUserInstituteIds(instituteId);
        //查询这些用户的所有大创项目
        for (UserPersonInfoEntity userPersonInfoEntity:userPersonInfoEntities){

            HashMap<String, Object> stringLongHashMap = new HashMap<String, Object>();

            stringLongHashMap.put("project_user_id",userPersonInfoEntity.getUserId());

            List<DeclareInfoEntity> declareInfoEntities = declareInfoService.selectByMap(stringLongHashMap);

            for (DeclareInfoEntity declareInfoEntity:declareInfoEntities){

                HashMap<String, Object> paramas = new HashMap<>();

                paramas.put("declareId",declareInfoEntity.getDeclareId());

                DeclareInfoModel query = query(paramas);

                total.add(query);
            }
        }
        //移除不通过和未提交的比赛项目
        if (total.size()>0) {

            for (DeclareInfoModel declareInfoModel : total) {

                //移除二级学院未审批和不通过的项目
                if (declareInfoModel.getDeclareInfoEntity().getProjectAuditApplyStatus() <3 ||
                        declareInfoModel.getDeclareInfoEntity().getAuditNoPass()==1||
                        declareInfoModel.getDeclareInfoEntity().getIsDel()==1
                ) {

                    total.remove(declareInfoModel);

                }
            }

        }
        return total;
    }
}
