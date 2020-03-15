package com.innovate.modules.innovate.utils;

import com.innovate.modules.innovate.common.BaseCalculate;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/9
 **/
public class BaseCalculateUtil {

    private static Set<BaseCalculate> baseCalculates = new HashSet<BaseCalculate>();
    private static Long calculateId;

    //添加计算对象
    public static void addObject(BaseCalculate baseCalculate) {
        baseCalculates.add(baseCalculate);
    }
    //计算
    public static void calculate() {
        for (BaseCalculate baseCalculate : baseCalculates) {
            baseCalculate.calculate(calculateId);
        }
    }

    public static void setCalculateId(Long calculateId) {
        BaseCalculateUtil.calculateId = calculateId;
    }
}
