package com.imooc.utils;

import java.util.Random;

/**
 * Created by guofei on 2017/11/16.
 */
public class KeyUtil {
    /*
    * 生成唯一的主键
    * 格式：时间+随机数
    * @return
       * synchronized 防止多线程 数字重复
    */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;  //6位的随机数
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
