package lab.galaxy.yahfa;

import android.util.Log;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2022-09-20 10:55:31
 * @author: sanbo
 */
public class YLog {

    public static final String TAG = "YAHFA";

    public static void v(String info) {
        println(Log.VERBOSE, TAG, info);
    }

    public static void d(String info) {
        println(Log.DEBUG, TAG, info);
    }

    public static void i(String info) {
        println(Log.INFO, TAG, info);
    }

    public static void w(String info) {
        println(Log.WARN, TAG, info);
    }

    public static void e(String info) {
        println(Log.ERROR, TAG, info);
    }

    public static void e(Throwable e) {
        println(Log.ERROR, TAG, Log.getStackTraceString(e));
    }


    public static void wtf(String info) {
        println(Log.ASSERT, TAG, info);
    }


    ////////////////////with tag//////////////////////
    public static void v(String tag, String info) {
        println(Log.VERBOSE, tag, info);
    }

    public static void d(String tag, String info) {
        println(Log.DEBUG, tag, info);
    }

    public static void i(String tag, String info) {
        println(Log.INFO, tag, info);
    }

    public static void w(String tag, String info) {
        println(Log.WARN, tag, info);
    }

    public static void e(String tag, String info) {
        println(Log.ERROR, tag, info);
    }

    public static void e(String tag, Throwable e) {
        println(Log.ERROR, tag, Log.getStackTraceString(e));
    }

    public static void wtf(String tag, String info) {
        println(Log.ASSERT, tag, info);
    }

    ////////////////////low level log//////////////////////
    public static int println(int priority, String tag, String msg) {
        if (!tag.startsWith(TAG)) {
            tag = TAG + "." + tag;
        }
        return Log.println(priority, tag, msg);
    }
}
