package lab.galaxy.yahfa;

import android.util.Log;

public class YD {

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

    public static int println(int priority, String tag, String msg) {
        return Log.println(priority, tag, msg);
    }
}
