package org.terrasdepontevedra.petra.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DimensUtil {

    public static int convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) (dp * (metrics.densityDpi / 160f));
    }

}
