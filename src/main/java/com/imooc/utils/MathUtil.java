package com.imooc.utils;

/**
 * Created by guofei on 2017/11/30.
 */
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    /*
    * 比较2个金额是否相等
    * @param d1
    * @param d2
    * @return
    */

    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        }else {
            return false;
        }
    }



}
