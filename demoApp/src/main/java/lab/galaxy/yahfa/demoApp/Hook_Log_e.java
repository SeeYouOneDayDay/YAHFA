package lab.galaxy.yahfa.demoApp;

import lab.galaxy.yahfa.YLog;

/**
 *  拦截log.e
 */
public class Hook_Log_e {
    public static String TAG = Hook_Log_e.class.getSimpleName();
    public static String className = "android.util.Log";
    public static String methodName = "e";
    public static String methodSig = "(Ljava/lang/String;Ljava/lang/String;)I";

    public static int hook(String tag, String msg) {
        YLog.w(TAG, "in Log.e(): " + tag + ", " + msg + "----- ---in hook()");
        return -99;
    }

    public static int backup(String tag, String msg) {
        YLog.w(TAG, "Log.e() should not be here ---in backup()");
        return 99;
    }
}
