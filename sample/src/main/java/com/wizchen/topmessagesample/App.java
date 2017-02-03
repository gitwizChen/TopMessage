package com.wizchen.topmessagesample;

import android.app.Application;

import com.wizchen.topmessage.util.TopActivityManager;

/**
 * Author: wizChen
 * Date: 2017-02-03 12:11
 * Description:
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(TopActivityManager.getInstance());
    }
}
