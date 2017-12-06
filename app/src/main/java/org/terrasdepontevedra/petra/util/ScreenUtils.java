package org.terrasdepontevedra.petra.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
