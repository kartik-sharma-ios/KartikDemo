package com.dimts.eticketing.utils;

import android.app.Application;

/**
 * Created by kartiksharma on 17/01/18.
 */

public class ApplicationCache {

    /**
     * Data in Cache
     */
    //Application Context
    private Application appContext;
    private static ApplicationCache applicationCache = null;

    private ApplicationCache() {

    }
    public static synchronized ApplicationCache getInstance() {

        if (applicationCache == null) {
            applicationCache = new ApplicationCache();
        }
        return applicationCache;
    }

    public void setAppContext(Application context) {
        this.appContext = context;
    }

    public Application getAppContext() {
        return this.appContext;
    }

    public void deleteAppCache() {
        applicationCache = null;
    }
}
