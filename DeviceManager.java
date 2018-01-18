package com.dimts.eticketing.Manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by kartiksharma on 17/01/18.
 */

public class DeviceManager {

    //Check Network Connection
    public static boolean isInternetAvailableOnDevice()
    {
        //Get Application Context
        Context appContext = CachingManager.getAppContext();

        //Get ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager)appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get NetworkInfo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return ((networkInfo == null || !networkInfo.isConnected()) ? false : true);
    }
}
