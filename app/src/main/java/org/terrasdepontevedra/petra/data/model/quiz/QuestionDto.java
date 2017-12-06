package org.terrasdepontevedra.petra.data.model.quiz;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class QuestionDto {
    @SerializedName("text")
    private final String mText;

    @SerializedName("answer")
    private final AnswerDto mAnswerDto;

    @SerializedName("answers")
    private final List<AnswerDto> mAnswers;

    public QuestionDto(String text, AnswerDto answerDto, List<AnswerDto> answers) {
        this.mText = text;
        this.mAnswerDto = answerDto;
        this.mAnswers = answers;
    }

    public String getText() {
        return mText;
    }

    public AnswerDto getAnswerDto() {
        return mAnswerDto;
    }

    public List<AnswerDto> getAnswersDto() {
        return mAnswers;
    }

}
