package com.dimts.eticketing.Manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * Created by kartiksharma on 17/01/18.
 */

public class PersistanceManager {

    private static void writeContentToSharedPreferences(String key, Object value) {

        Context context = CachingManager.getAppContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (value instanceof String) {
            editor.putString(key, ((String) value).trim());
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        }
    }

    private static Object getContentFromSharedPreferences(String key, Class<? extends Object> classType) {

        Object object = null;
        Context context = CachingManager.getAppContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (classType.equals(String.class)) {

            object = sharedPreferences.getString(key, "");
        } else if (classType.equals(Integer.class)) {

            object = sharedPreferences.getInt(key, 0);
        } else if (classType.equals(Float.class)) {

            object = sharedPreferences.getFloat(key, 0.00f);
        } else if (classType.equals(boolean.class)) {

            object = sharedPreferences.getBoolean(key, false);
        } else if (classType.equals(long.class)) {

            object = sharedPreferences.getLong(key, 0L);
        } else if (classType.equals(Set.class)) {

            object = sharedPreferences.getStringSet(key, null);
        }

        return object;
    }

    public static void removeContentFromSharedPref(String key) {

        Context context = CachingManager.getAppContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
    }
}
