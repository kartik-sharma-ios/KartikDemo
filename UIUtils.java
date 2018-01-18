package com.dimts.eticketing.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.dimts.eticketing.Manager.CachingManager;
import com.dimts.eticketing.R;

/**
 * Created by kartiksharma on 18/01/18.
 */

public class UIUtils {

    private static Toast toast;


    //Show Soft Keyboard
    public static void showSoftKeyboard(EditText editText)
    {
        //Request Focus
        editText.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void alertBox(Context context, String msg)
    {
        alertBox(context, msg, 0);
    }

    public static void alertBox(final Context context, String msg, final int code) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setMessage(msg);

            AlertDialog alert = builder.create();
            alert.show();
            alert.getButton(DialogInterface.BUTTON_POSITIVE);
        } catch (Exception exception) {
//            LogManager.getInstance().addLog(2017, exception.toString(), exception);
        }
    }

    //Hide Soft Keyboard
    public static void hideSoftKeyboard(Activity activity)
    {
        //Get View
        View view = activity.getWindow().getDecorView();

        //Hide Soft Keyboard
        hideSoftKeyboard(view);
    }

    //Hide Soft Keyboard
    public static void hideSoftKeyboard(View view)
    {
        if(view == null)
        {
            return;
        }

        //Get Window Token
        IBinder token = view.getWindowToken();

        //Get InputMethodManager
        InputMethodManager inputMethodManager = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        //Gain Focus on Current View
        view.requestFocus();

        //Hide Soft Input
        inputMethodManager.hideSoftInputFromWindow(token, InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    //Show Toast
    public static void showToast(int messageId)
    {
        //Get Application Context
        Context appContext = CachingManager.getAppContext();

        showToast(appContext.getString(messageId));
    }

    //Show Toast
    public static void showToast(String message)
    {
        //Check Toast
        if(toast != null)
        {
            //Get View of Toast
            View view = toast.getView();

            if(view.isShown())
            {
                return;
            }
        }

        //Get Application Context
        Context appContext = CachingManager.getAppContext();

        //Create Toast
        toast = Toast.makeText(appContext, message, Toast.LENGTH_SHORT);

        //Show toast
        toast.show();
    }

    /**
     * Shows Progress Bar Dialog
     * @param activityContext
     * @return
     */
    public static Dialog showProgressDialog(Context activityContext)
    {
        Dialog dialog = null;

        try
        {
            dialog = new Dialog(activityContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.layout_progress_bar);
            dialog.setCancelable(false);
            dialog.show();
        }
        catch (Exception exception)
        {
            //Consume it
            exception.printStackTrace();
        }

        return dialog;
    }

    /**
     * Whether the Dialog instance is there and it is showing.
     * @param dialog Dialog object to check
     * @return true if instance exists and is showing else false
     */
    public static boolean isDialogShowing(Dialog dialog)
    {
        boolean isDialogShowing = false;

        try
        {
            if (dialog != null && dialog.isShowing())
            {
                isDialogShowing = true;
            }
        }
        catch(Exception exception)
        {
            //Consume exception
        }

        return isDialogShowing;
    }

    public static void dismissDialog(Dialog dialog)
    {
        try
        {
            if (dialog != null && dialog.isShowing())
            {
                dialog.dismiss();
                dialog = null;
            }
        }
        catch (Exception exception)
        {
            //Consume it
        }
    }

    public static void showToastNotification(Context context, String message, String title, boolean isLongDuration)
    {
        int duration = 0;

        if (isLongDuration)
        {
            duration = Toast.LENGTH_LONG;
        }
        else
        {
            duration = Toast.LENGTH_SHORT;
        }

        if (message!=null && !message.isEmpty())
        {
            if (title != null)
            {
                String toastMessage = title + "\n\n" + message;
                Toast.makeText(context, toastMessage, duration).show();
            }
            else
            {
                String toastMessage = message;
                Toast.makeText(context, toastMessage, duration).show();
            }
        }
    }


    public static void hideViewByGone(View view)
    {
        if (view != null && view.getVisibility() == View.VISIBLE)
        {
            view.setVisibility(View.GONE);
        }
    }

    public static void hideViewByInvisible(View view)
    {
        if (view != null && view.getVisibility() == View.VISIBLE)
        {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void showView(View view)
    {
        if (view != null && (view.getVisibility() == View.INVISIBLE || view.getVisibility() == View.GONE))
        {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Show alert message
     * @param context activity context
     * @param header header of alert box
     * @param msg message for alert
     */
    public static void alertBox(final Activity context, String header, String msg)
    {
        if( !context.isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
            builder.setCancelable(false);
            if (!AppUtil.isEmpty(header)) {
                builder.setTitle(header);
            }
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setMessage(msg);

            AlertDialog alert = builder.create();
            alert.setCancelable(false);
            alert.show();
        }
    }

    /**
     * Show alert message
     * @param context activity context
     * @param header header of alert box
     * @param msg message for alert
     */
    public static AlertDialog alertBox(final Activity context, String header, String msg,String actionButtonText, DialogInterface.OnClickListener onClickListener)
    {
        AlertDialog alert = null;

        if( !context.isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
            builder.setCancelable(false);
            if (!AppUtil.isEmpty(header)) {
                builder.setTitle(header);
            }
            builder.setPositiveButton(actionButtonText, onClickListener);
            builder.setMessage(msg);

            alert = builder.create();
            alert.setCancelable(false);
            alert.show();
        }

        return alert;
    }
    /**
     * Show alert message
     * @param context activity context
     * @param header header of alert box
     * @param msg message for alert
     */
    public static AlertDialog alertBox(final Activity context, String header, String msg,String actionButtonText,String noText ,DialogInterface.OnClickListener onClickListener)
    {
        AlertDialog alert = null;
        if( !context.isFinishing())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
            builder.setCancelable(false);
            if (!AppUtil.isEmpty(header)) {
                builder.setTitle(header);
            }
            builder.setPositiveButton(actionButtonText, onClickListener);
            builder.setNegativeButton(noText, onClickListener);

            builder.setMessage(msg);

            alert = builder.create();
            alert.setCancelable(false);
            alert.show();
        }

        return alert;
    }

    public static void hideKeyboard(View view, Context context)
    {
        InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
