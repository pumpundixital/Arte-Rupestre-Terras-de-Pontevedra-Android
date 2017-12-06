package org.terrasdepontevedra.petra.domain.usecase.base;

import org.terrasdepontevedra.petra.util.interfaces.Action;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public interface UseCaseBase<TUseCase extends UseCaseBase<TUseCase, TParam, TResult, TResultMap>, TParam, TResult, TResultMap>
        extends
        UseCasePrePostActions<TUseCase>,
        UseCaseErrorAction<TUseCase>,
        UseCaseParams<TUseCase, TParam> {

    @Override
    TUseCase setParams(TParam param);

    @Override
    TUseCase setPreAndPostActions(Action preAction, Action postAction);

    @Override
    TUseCase setErrorAction(Action1<Throwable> throwableAction1);

    UseCaseBase<TUseCase, TParam, TResult, TResultMap> map(Function<TResult, TResultMap> tResultTResultMapFunction);

    void execute(Observable<TResult> tResultObservable, final Action1<TResultMap> itinerarySubscriber);

}
