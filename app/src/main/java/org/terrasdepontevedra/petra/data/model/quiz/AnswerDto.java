package org.terrasdepontevedra.petra.data.model.quiz;

import com.google.gson.annotations.SerializedName;


public class AnswerDto {
    @SerializedName("text")
    private final String mText;

    public AnswerDto(String text) {
        this.mText = text;
    }

    public String getText() {
        return mText;
    }
}
