package org.terrasdepontevedra.petra.util.services;

import android.content.Context;
import android.support.annotation.StringRes;

public class TranslatorServiceImpl implements TranslatorService {
    private final Context mContext;

    public TranslatorServiceImpl(Context context) {
        mContext = context;
    }

    @Override
    public String from(@StringRes int idRes) {
        return mContext.getResources().getString(idRes);
    }
}
