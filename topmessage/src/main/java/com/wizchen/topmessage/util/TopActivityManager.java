package com.wizchen.topmessage.util;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Author: wizChen
 * Date: 2017-02-01 20:52
 * Description: help to get the current activity of the application
 */
public class TopActivityManager {
    private WeakReference<Activity> mCurrentActivity;

    private static class TopActivityManagerHolder {
        private static final TopActivityManager INSTANCE = new TopActivityManager();
    }

    public static TopActivityManager getInstance() {
        return TopActivityManagerHolder.INSTANCE;
    }

    private TopActivityManager() {
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (mCurrentActivity != null) {
            currentActivity = mCurrentActivity.get();
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        mCurrentActivity = new WeakReference<>(activity);
    }
}
