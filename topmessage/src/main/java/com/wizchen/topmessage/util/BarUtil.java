package com.wizchen.topmessage.util;

import android.content.Context;

/**
 * Author: wizChen
 * Date: 2017-01-28 19:14
 * Description: a util about bar
 */

public class BarUtil {

    private BarUtil() {
    }

    /**
     * get the height of the status' bar
     *
     * @param context the context
     * @return the height
     */
    public static int getStatusBarHeight(Context context) {
        int result = -1;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
