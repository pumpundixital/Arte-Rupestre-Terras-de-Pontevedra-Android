package org.terrasdepontevedra.petra.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

public class ActivityUtils {
    public static void openActivityWith(Activity activity, Intent intent, View transitionView,
                                        String transactionName) {
        ActivityOptions transitionActivityOptions =
                makeSceneTransitionAnimation(
                        activity,
                        transitionView,
                        transactionName
                );
        activity.startActivity(intent, transitionActivityOptions.toBundle());
    }
}
