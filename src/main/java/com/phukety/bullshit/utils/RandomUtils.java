package com.phukety.bullshit.utils;

import java.util.Random;

/**
 * 随机工具类
 */
public class RandomUtils {
    /**
     * 获取处于最小值最大值范围内的随机数
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }
}
