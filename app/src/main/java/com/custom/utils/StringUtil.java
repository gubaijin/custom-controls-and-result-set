package com.custom.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Kevin on 2016/5/13.
 */
public class StringUtil {

    //结合下面的方法可以来或者随机并不重复的控件ID
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
