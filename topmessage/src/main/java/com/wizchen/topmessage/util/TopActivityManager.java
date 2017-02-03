package com.wizchen.topmessage.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Author: wizChen
 * Date: 2017-02-01 20:52
 * Description: help to get the current activity of the application
 */
public class TopActivityManager implements Application.ActivityLifecycleCallbacks {
    private WeakReference<Activity> mCurrentActivity;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        setCurrentActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

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
