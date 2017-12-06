package org.terrasdepontevedra.petra.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;


public class LanguageCollectorImpl implements LanguageCollector {

    private static final String PREFERENCES_FILE = "PREFERENCES_FILE";
    private static final String LANGUAGE = "LANGUAGE";
    private final SharedPreferences mSharedPreferences;

    public LanguageCollectorImpl(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    @Override
    public String getLanguage() {
        String languageDefault = Locale.getDefault().getLanguage();
        if (languageDefault.equals("es"))
            languageDefault = "/";
        return mSharedPreferences.getString(LANGUAGE, languageDefault);
    }

    @Override
    public void setLanguage(String language) {
        mSharedPreferences.edit().putString(LANGUAGE, language).apply();
    }
}
