package org.terrasdepontevedra.petra.util;

import android.os.Environment;

import org.terrasdepontevedra.petra.domain.model.base.Audio;

import java.io.File;


public class FileUtils {
    public static String generateUrlFrom(Audio audio) {
        String[] split = audio.getUrl().asString().split("/");
        String name = split[split.length - 1];
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio/" + name);
        if (file.exists())
            return file.getAbsolutePath();
        else
            return audio.getUrl().asString();
    }

}
