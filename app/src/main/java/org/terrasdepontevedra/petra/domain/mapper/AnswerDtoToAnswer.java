package org.terrasdepontevedra.petra.domain.mapper;

import org.terrasdepontevedra.petra.data.model.quiz.AnswerDto;
import org.terrasdepontevedra.petra.domain.model.quiz.Answer;
import org.terrasdepontevedra.petra.domain.model.quiz.AnswerCollection;

import java.util.List;

class AnswerDtoToAnswer {
    public static Answer map(AnswerDto answerDto) {
        return Answer.from(answerDto.getText());
    }

    public static AnswerCollection map(List<AnswerDto> answersDto) {
        AnswerCollection answerCollection = new AnswerCollection();
        for (AnswerDto answerDto : answersDto) {
            answerCollection.add(map(answerDto));
        }
        return answerCollection;
    }
}
