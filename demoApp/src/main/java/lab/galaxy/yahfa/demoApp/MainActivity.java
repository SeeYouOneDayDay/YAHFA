package lab.galaxy.yahfa.demoApp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Method;

import lab.galaxy.yahfa.HookMain;
import lab.galaxy.yahfa.YLog;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.hookBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Method hook = Hook_Log_e.class.getDeclaredMethod("hook", String.class, String.class);
                    Method backup = Hook_Log_e.class.getDeclaredMethod("backup", String.class, String.class);
                    HookMain.findAndBackupAndHook(Log.class, Hook_Log_e.methodName, Hook_Log_e.methodSig, hook, backup);

                   int resu= Log.e("YAHFA","test in MainActivity");
                   YLog.d("result:"+resu);
                } catch (Throwable e) {
                    YLog.e(TAG, e);
                }
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 200; i++) {
                    doWork(i);
                }
            }
        });
    }

    void doWork(int currentIndex) {
        StringBuffer sb =new StringBuffer();
        sb
                .append("call Log.e():                ").append(Log.e("tt","xx"))
                .append("\r\ncall Log.e() back:       ").append(Hook_Log_e.backup("tt","xx"))
                // String.startsWith() should be hooked
                .append("\r\nfoo startsWith f is:      ").append("foo".startsWith("f"))
                // ClassWithVirtualMethod.tac() should be hooked
                .append("\r\nvirtual tac a,b,c,d, got: ").append(new ClassWithVirtualMethod().tac("a", "b", "c", "d"))
               //ClassWithStaticMethod.tac() should be hooked
                .append("\r\nstatic tac a,b,c,d, got:  ").append(ClassWithStaticMethod.tac("a", "b", "c", "d"))
                .append("\r\nclass ctor and get field: ").append(new ClassWithCtor("param").getField())
        ;

        YLog.d(TAG, "===========["+currentIndex+"]=========\r\n" + sb.toString());
    }
}
