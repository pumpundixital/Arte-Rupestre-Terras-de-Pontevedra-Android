package org.terrasdepontevedra.petra.domain.params;

import android.support.annotation.RawRes;

public class GetQuizParams {
    @RawRes
    private final int mId;
    private final String mTitle;

    public GetQuizParams(int id, String title) {
        mId = id;
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}
