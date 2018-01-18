package com.dimts.eticketing.Application;

import android.app.Application;

import com.dimts.eticketing.Manager.ApplicationManager;

/**
 * Created by Heena Aggarwal on 18-01-2018.
 */

public class ApplicationClass extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationManager.prepareApplication(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
