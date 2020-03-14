package com.innovate.common.utils;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/24
 **/
public class StringUtils {
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping
                && ((name.charAt(0) >= 'A' && name.charAt(0) <= 'Z')
                || name.indexOf("_") >= 0)) {
            return underlineToCamelhump(name);
        }
        return name;
    }

    /**
     * 将下划线风格替换为驼峰风格
     *
     * @param inputString
     * @return
     */
    public String underlineToCamelhump(String inputString) {
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '_') {
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }
}
