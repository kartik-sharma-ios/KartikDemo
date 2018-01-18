package com.dimts.eticketing.utils;

import android.support.annotation.Nullable;

import java.io.File;
import java.util.List;

/**
 * Created by kartiksharma on 17/01/18.
 */

public class AppUtil {

    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isStringEmpty(@Nullable String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.trim().length() == 0 || str.equals(" ");
    }

    /**
     * Return true if the string is not empty
     * @param str the string to be examined
     * @return true if str is not noll or length > 0
     * else
     * false
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * To check collection list is empty or not
     * @param list instance of list
     * @param <T> generic type of list
     * @return true -> 	if list is null or size == 0
     * 				else
     * 					false
     */
    public static <T> boolean isListEmpty(List<T> list)
    {
        return list == null || list.isEmpty();
    }

    public static <T> boolean isNotEmptyList(List<T> list){
        return !isListEmpty(list);
    }

    /**
     * Checks if the String is empty (null or blank) or contains data
     * @param data data to check
     * @return true -> if string is empty
     * false -> if contains data
     */
    public static boolean isEmpty(String data)
    {
        return !containsData(data);
    }

    /**
     * Checks if the String is empty (null or blank) or contains data
     * @param data data to check is contains string char
     * @return true-> if contains data
     */
    public static boolean containsData(String data)
    {
        boolean containsData = true;
        if (data == null || data.trim().equals(""))
        {
            containsData = false;
        }

        return containsData;
    }

}
