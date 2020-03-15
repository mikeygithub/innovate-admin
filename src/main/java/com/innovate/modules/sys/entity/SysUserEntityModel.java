package com.innovate.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName:innovate-admin
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-26上午8:54
 * @Describe:用于二级学院批量添加用户，从文件中读取出来的数据会被重命名
 **/
@Data
public class SysUserEntityModel implements Serializable {

    /**
     * 用户名--账号
     */
    private String __EMPTY;

    /**
     * 用户名--姓名
     */
    private String __EMPTY_1;

    /**
     * 手机号
     */
    private String __EMPTY_2;

    /**
     * 邮箱
     */
    private String __EMPTY_3;

    /**
     * 用户类型
     */
    private String __EMPTY_4;

}
