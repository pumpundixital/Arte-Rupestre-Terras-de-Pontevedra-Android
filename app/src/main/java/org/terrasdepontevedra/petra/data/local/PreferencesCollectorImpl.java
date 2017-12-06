package org.terrasdepontevedra.petra.data.local;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesCollectorImpl implements PreferencesCollector {
    private static final String PREFERENCES_FILE = "PREFERENCES_FILE";
    private static final String FIRST_TIME = "FIRST_TIME";
    private static final String PETRA_ACTIVE = "PETRA_ACTIVE";
    private static final String DOWNLOADED_CONTENT = "DOWNLOADED_CONTENT";

    private final SharedPreferences mSharedPreferences;

    public PreferencesCollectorImpl(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isFirstTime() {
        return mSharedPreferences.getBoolean(FIRST_TIME, true);
    }

    @Override
    public void setFirstTime(boolean firstTime) {
        mSharedPreferences.edit().putBoolean(FIRST_TIME, firstTime).apply();
    }

    @Override
    public void setPetraActive(boolean petraActive) {
        mSharedPreferences.edit().putBoolean(PETRA_ACTIVE, petraActive).apply();
    }

    @Override
    public boolean isPetraActive() {
        return mSharedPreferences.getBoolean(PETRA_ACTIVE, true);
    }

    @Override
    public boolean isContentDownloaded() {
        return mSharedPreferences.getBoolean(DOWNLOADED_CONTENT, false);
    }

    @Override
    public void setContentDownloaded(boolean contentDownloaded) {
        mSharedPreferences.edit().putBoolean(DOWNLOADED_CONTENT, contentDownloaded).apply();
    }
}
