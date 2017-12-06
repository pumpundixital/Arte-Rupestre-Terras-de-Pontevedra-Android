package org.terrasdepontevedra.petra.domain.mapper;

import org.terrasdepontevedra.petra.data.model.quiz.QuestionDto;
import org.terrasdepontevedra.petra.domain.model.base.Title;
import org.terrasdepontevedra.petra.domain.model.quiz.Question;
import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;

import java.util.List;

public class QuestionDtoToQuiz {
    public static Quiz map(List<QuestionDto> questionsDto, String title) {
        Quiz quiz = new Quiz(Title.from(title));
        for (QuestionDto questionDto : questionsDto) {
            quiz.add(map(questionDto));
        }
        return quiz;
    }

    private static Question map(QuestionDto questionDto) {
        return new Question(
                questionDto.getText(),
                AnswerDtoToAnswer.map(questionDto.getAnswerDto()),
                AnswerDtoToAnswer.map(questionDto.getAnswersDto())
        );
    }
}
