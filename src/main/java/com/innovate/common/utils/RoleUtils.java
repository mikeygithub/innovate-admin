package com.innovate.common.utils;

import org.apache.commons.beanutils.ConvertUtils;


/**
 * @ProjectName:innovate-admin
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-8上午10:58
 * @Describe: 用户角色工具类
 **/
public class RoleUtils {

    /**
     * 判断批量导入用户时，输入的用户类型是否合法
     * @param roleIds
     * @return
     */
    public static boolean checkRole (String roleIds) {

        if (roleIds == null || roleIds.length() == 0) {
            return false;
        }

        //截取字符串
        String[] idsStr = roleIds.split(",");
        //转换long类型的数组
        Long rIds[] = (Long[]) ConvertUtils.convert(idsStr, Long.class);


        for (long Ids : rIds) {
            if (Ids != 2 && Ids != 3 && Ids != 11 && Ids != 12) {
                return false;
            }
        }

        return true;
    }
}