package lab.galaxy.yahfa.demoApp;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import org.lsposed.hiddenapibypass.HiddenApiBypass;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import dalvik.system.DexClassLoader;
import lab.galaxy.yahfa.HookMain;

/**
 * Created by liuruikai756 on 30/03/2017.
 */

public class MainApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        if (Build.VERSION.SDK_INT > 27) {
            HiddenApiBypass.unseal(this);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            String sourceName = "demoPlugin-debug.apk";
            extractAssets(this, sourceName);

            ClassLoader classLoader = getClassLoader();
            DexClassLoader dexClassLoader = new DexClassLoader(
//                    new File(Environment.getExternalStorageDirectory(), "demoPlugin-debug.apk").getAbsolutePath(),
                    getFileStreamPath(sourceName).getAbsolutePath(),
                    getCodeCacheDir().getAbsolutePath(), null, classLoader);
            Log.d("YAHFA.app", "dexClassLoader:" + dexClassLoader);
            HookMain.doHookDefault(dexClassLoader, classLoader);
        } catch (Throwable e) {
            Log.e("YAHFA.app", Log.getStackTraceString(e));
        }
    }

    public static void extractAssets(Context context, String sourceName) {
        AssetManager am = context.getAssets();
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = am.open(sourceName);
            File extractFile = context.getFileStreamPath(sourceName);
            fos = new FileOutputStream(extractFile);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.flush();
        } catch (Throwable e) {
            Log.e("YAHFA.app", Log.getStackTraceString(e));
        } finally {
            closeSilently(is);
            closeSilently(fos);
        }

    }

    private static void closeSilently(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Throwable e) {
            // ignore
        }
    }
}
