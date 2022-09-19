package lab.galaxy.yahfa.demoApp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;

import dalvik.system.DexClassLoader;
import lab.galaxy.yahfa.HookMain;

/**
 * Created by liuruikai756 on 30/03/2017.
 */

public class MainApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        try {
        /*
        Build and put the demoPlugin apk in sdcard before running the demoApp
         */
            ClassLoader classLoader = getClassLoader();
            DexClassLoader dexClassLoader = new DexClassLoader(
//                    new File(Environment.getExternalStorageDirectory(), "demoPlugin-debug.apk").getAbsolutePath(),
                    new File("/sdcard/demoPlugin-debug.apk").getAbsolutePath(),
                    getCodeCacheDir().getAbsolutePath(), null, classLoader);
            Log.d("YAHFA.app", "dexClassLoader:" + dexClassLoader);
            HookMain.doHookDefault(dexClassLoader, classLoader);
        } catch (Throwable e) {
            Log.e("YAHFA.app", Log.getStackTraceString(e));
        }
    }
}
