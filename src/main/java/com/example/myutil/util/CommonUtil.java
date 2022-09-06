package com.example.myutil.util;


import org.apache.commons.lang3.StringUtils;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class CommonUtil {

    public static String md5(String text) {
        if (StringUtils.isNotBlank(text)) {
            return Hashing.md5().hashBytes(text.getBytes(Charsets.UTF_8)).toString();
        } else {
            return StringUtils.EMPTY;
        }
    }



}
