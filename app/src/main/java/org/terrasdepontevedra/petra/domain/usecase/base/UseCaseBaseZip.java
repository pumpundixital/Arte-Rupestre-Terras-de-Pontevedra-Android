package org.terrasdepontevedra.petra.domain.usecase.base;

import org.terrasdepontevedra.petra.util.interfaces.Action;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public interface UseCaseBaseZip<TUseCase extends UseCaseBaseZip<TUseCase, TParam, TResult, TResultZip, TResultMap>, TParam, TResult, TResultZip, TResultMap>
        extends
        UseCasePrePostActions<TUseCase>,
        UseCaseErrorAction<TUseCase> {


    @Override
    TUseCase setPreAndPostActions(Action preAction, Action postAction);

    @Override
    TUseCase setErrorAction(Action1<Throwable> throwableAction1);

    TUseCase map(BiFunction<TResult, TResultZip, TResultMap> tResultTResultMapFunction);

    TUseCase setParams(TParam params);

    void execute(Observable<TResult> tResultObservable, Observable<TResultZip> tResultObservableZip, final Action1<TResultMap> itinerarySubscriber);

}
