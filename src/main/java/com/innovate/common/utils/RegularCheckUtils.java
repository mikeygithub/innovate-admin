package com.innovate.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName:innovate-admin
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-27下午3:12
 * @Describe: 正则校验工具类
 **/
public class RegularCheckUtils {

    /**
     * 校验手机号
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
//        if (phone.length() != 11) {
////            System.out.println("手机号应为11位数");
//            return false;
//        } else {
//            Pattern p = Pattern.compile(regex);
//            Matcher m = p.matcher(phone);
//            boolean isMatch = m.matches();
//            if (!isMatch) {
////                System.out.println("请填入正确的手机号");
//            }
//            return isMatch;
//        }
    }

    /**
     * 校验邮箱
     */
    public static boolean isEmail(String email) {
        String regex = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void main(String[] args) {
//        System.out.println(isPhone("18376031350@qq"));
//        System.out.println(isEmail("22"));
    }
}
