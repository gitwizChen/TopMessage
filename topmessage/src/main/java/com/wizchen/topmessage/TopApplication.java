package com.wizchen.topmessage;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.wizchen.topmessage.util.TopActivityManager;

/**
 * Author: wizChen
 * Date: 2017-02-01 20:48
 * Description:
 */

public class TopApplication extends Application {

    protected static TopApplication mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                TopActivityManager.getInstance().setCurrentActivity(activity);
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
        });
    }

    public static TopApplication getInstance() {
        return mAppContext;
    }
}
