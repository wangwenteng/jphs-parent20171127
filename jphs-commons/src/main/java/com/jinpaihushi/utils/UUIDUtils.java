package com.jinpaihushi.utils;

import java.util.UUID;

public class UUIDUtils {

    /**
     * 自动生成ID
     */
    public static String getId() {

        return UUID.randomUUID().toString().replace("-", "");

    }

}
