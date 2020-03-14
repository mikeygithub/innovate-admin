package com.innovate.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName:innovate-admin
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-26上午8:50
 * @Describe:用于学院账号批量导入学院用户-项目负责人或学生
 **/
@Data
public class SysUserModel implements Serializable {
    private Long erInstituteId;//所属学院
    private List<SysUserEntityModel> batchSaveList;//要保存的用户集合
}
