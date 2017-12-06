package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.PetraRepository;
import org.terrasdepontevedra.petra.data.model.quiz.QuestionDto;
import org.terrasdepontevedra.petra.domain.mapper.QuestionDtoToQuiz;
import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;
import org.terrasdepontevedra.petra.domain.params.GetQuizParams;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBaseImpl;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import java.util.List;


public class GetQuizUseCaseImpl
        extends UseCaseBaseImpl<GetQuizUseCase, PetraRepository, GetQuizParams, List<QuestionDto>, Quiz>
        implements GetQuizUseCase {
    public GetQuizUseCaseImpl(PetraRepository petraRepository) {
        super(petraRepository);
    }

    @Override
    public void execute(Action1<Quiz> tResultMapSubscriber) {
        super
                .map(questionDto -> QuestionDtoToQuiz.map(questionDto, mParam.getTitle()))
                .execute(mRepository.getQuiz(mParam.getId()), tResultMapSubscriber);
    }
}
