package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.model.quiz.QuestionDto;
import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;
import org.terrasdepontevedra.petra.domain.params.GetQuizParams;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBase;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseExecute;

import java.util.List;

public interface GetQuizUseCase
        extends UseCaseBase<GetQuizUseCase, GetQuizParams, List<QuestionDto>, Quiz>,
        UseCaseExecute<Quiz> {
}
