package com.dimts.eticketing.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

import static android.support.v4.app.ActivityCompat.requestPermissions;

public final class RuntimePermissionHelper {

    private static RuntimePermissionHelper runtimePermissionHelper;
    public static final int PERMISSION_REQUEST_CODE = 1;
    private Activity activity;

    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    private ArrayList<String> requiredPermissions;
    private ArrayList<String> unGrantedPermissions = new ArrayList<>();

    private RuntimePermissionHelper(Activity activity)  {
        this.activity = activity;
    }

    public static synchronized RuntimePermissionHelper getInstance(Activity activity){
        if(runtimePermissionHelper == null) {
            runtimePermissionHelper = new RuntimePermissionHelper(activity);
        }
        return runtimePermissionHelper;
    }

    /**
     * permission initialization for all type of permission
     */
    private void initAllPermissions() {
        requiredPermissions = new ArrayList<>();
        requiredPermissions.add(PERMISSION_WRITE_EXTERNAL_STORAGE);
        requiredPermissions.add(PERMISSION_RECORD_AUDIO);
        //Add all the required permission in the list
    }

    /**
     * init permission of external storage
     */
    private void initPermissions() {
        requiredPermissions = new ArrayList<>();
        requiredPermissions.add(PERMISSION_CAMERA);
        requiredPermissions.add(PERMISSION_WRITE_EXTERNAL_STORAGE);
        //Add all the required permission in the list
    }

    /**
     * request for permission,if user denied
     * @param activity  instance
     */
    public void requestPermissionsIfDenied(final MainActivity activity){
        unGrantedPermissions = getUnGrantedPermissionsList();
        if(canShowPermissionRationaleDialog()){
            showMessageOKCancel(activity.getResources().getString(R.string.permission_message),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            askPermissions(activity);
                        }
                    });
            return;
        }
        askPermissions(activity);
    }

    /**
     * request for permission,if user denied
     * @param activity  instance
     */
    public void requestPermissionsIfDeniedForPhoneCall(final MainActivity activity){
        unGrantedPermissions = getUnGrantedPermissionsList();
        if(canShowPermissionRationaleDialog()){
            showMessageOKCancel("Permission required for make phone call",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            askPermissions(activity);
                        }
                    });
            return;
        }
        askPermissions(activity);
    }
/*
    public void requestPermissionIfDenied(final String permission){
        if(canShowPermissionRationaleDialog(permission)){
            showMessageOKCancel(activity.getResources().getString(R.string.permission_message),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            askPermission(permission);
                        }
                    });
            return;
        }
        askPermission(permission);
    }*/

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * show permission dialog
     * @return flag for show dialog
     */
    public boolean canShowPermissionRationaleDialog() {
        boolean shouldShowRationale = false;
        for(String permission: unGrantedPermissions) {
            boolean shouldShow = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            if(shouldShow) {
                shouldShowRationale = true;
            }
        }
        return shouldShowRationale;
    }

    /**
     * show permission dialog
     * @return flag for show dialog
     */
   /* public boolean canShowPermissionRationaleDialog(String permission) {
        boolean shouldShowRationale = false;
            boolean shouldShow = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            if(shouldShow) {
                shouldShowRationale = true;
        }
        return shouldShowRationale;
    }
*/
    /**
     * ask for permission
     * @param activity instance
     */
    private void askPermissions(MainActivity activity) {
        if(unGrantedPermissions.size()>0) {
           requestPermissions(activity, unGrantedPermissions.toArray(new String[unGrantedPermissions.size()]), PERMISSION_REQUEST_CODE);
        }
    }

   /* private void askPermission(String permission) {
            requestPermissions(activity, new String[] {permission}, PERMISSION_REQUEST_CODE);
    }*/

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton(R.string.ok, okListener)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        //TODO: Handle cancel scenario when rationale dailog is also cancelled
//                        Intent intent = new Intent(activity,ErrorActivity.class);
//                        intent.putExtra("permissions_denied",true);
//                        activity.startActivity(intent);
//                        activity.finish();
                    }
                })
                .create()
                .show();
    }

    /**
     * flag for if all permission available
     * @return true -> if yes
     * else
     *  false
     */
    /*public boolean isAllPermissionAvailable() {
        boolean isAllPermissionAvailable = true;
        initAllPermissions();
        for(String permission : requiredPermissions){
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED){
                isAllPermissionAvailable = false;
                break;
            }
        }
        return isAllPermissionAvailable;
    }
*/
    public ArrayList<String> getUnGrantedPermissionsList() {
        ArrayList<String> list = new ArrayList<>();
        for(String permission: requiredPermissions) {
            int result = ActivityCompat.checkSelfPermission(activity, permission);
            if(result != PackageManager.PERMISSION_GRANTED) {
                list.add(permission);
            }
        }
        return list;
    }

    /**
     * Flag for is permission available
     * @param permission permission type
     * @return flag
     */
    public boolean isPermissionAvailable(String permission) {
        boolean isPermissionAvailable = true;
        initPermissions();

        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED){
                isPermissionAvailable = false;
            }
        return isPermissionAvailable;
    }
}