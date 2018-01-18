package com.dimts.eticketing.Manager;

import android.app.Application;
import android.content.Context;

import com.dimts.eticketing.utils.ApplicationCache;

/**
 * Created by kartiksharma on 17/01/18.
 */

public class CachingManager {

    public static void saveAppContext(Application context) {

        ApplicationCache applicationCache = ApplicationCache.getInstance();
        applicationCache.setAppContext(context);
    }

    public static Context getAppContext() {

        ApplicationCache applicationCache = ApplicationCache.getInstance();
        return applicationCache.getAppContext();
    }

    public static void removeApplicationCache() {

        ApplicationCache.getInstance().deleteAppCache();
    }
}
