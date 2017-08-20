package com.sunhdj.doodlebundle;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by huangdaju on 17/8/20.
 */

public class Utils {

    public static String savePath(Context context) {
        getApplicationPath(context);
        String packageName = context.getPackageName();
        String path = Environment.getExternalStorageDirectory().getPath() + File.separator + packageName;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = path + File.separator + System.currentTimeMillis() + ".png";
        return filePath;
    }

    public static String getApplicationPath(Context context) {
        if (context == null) {
            return null;
        }

        Context appContext = context.getApplicationContext();
        if (appContext == null) {
            return null;
        }

        File file = appContext.getFilesDir();
        if (file == null) {
            return null;
        }

        return file.getAbsolutePath();
    }


}
