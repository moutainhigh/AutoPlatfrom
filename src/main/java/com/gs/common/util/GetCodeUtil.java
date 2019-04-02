package com.gs.common.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by xiao-kang on 2017/5/24.
 */
public class GetCodeUtil {

    public static String getCode(int passLength, int type){
        StringBuffer buffer = null;
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        switch (type)
        {
            case 0:
                buffer = new StringBuffer("0123456789");
                break;
            case 1:
                buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
                break;
            case 2:
                buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            case 3:
                buffer = new StringBuffer(
                        "0123456789abcdefghijklmnopqrstuvwxyz");
                break;
            case 4:
                buffer = new StringBuffer(
                        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
                sb.append(buffer.charAt(r.nextInt(buffer.length() - 10)));
                passLength -= 1;
                break;
            case 5:
                String s = UUID.randomUUID().toString();
                sb.append(s, 0, 8).append(s, 9, 13).append(s, 14, 18).append(s, 19, 23).append(s.substring(24));
            default:
        }

        if (type != 5)
        {
            int range = 0;
            if (buffer != null) {
                range = buffer.length();
            }
            for (int i = 0; i < passLength; ++i)
            {
                if (buffer != null) {
                    sb.append(buffer.charAt(r.nextInt(range)));
                }
            }
        }
        return sb.toString();
    }
}
