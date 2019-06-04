package com.fxpcxt.jwt.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/**
 * @author 
 * @version 创建时间：2018/5/1 00:54
 */
public class IDUtil {

    private static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    private static final int STANDARD_BYTE_LENGTH = 8;

    private static final int MAX_RANDOM_NUMBERID = 100;


    /**
     * shortUUID
     * @return
     */
    public static String shortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < STANDARD_BYTE_LENGTH; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 生成uuid
     * @return
     */
    public static String uuid() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }


}
