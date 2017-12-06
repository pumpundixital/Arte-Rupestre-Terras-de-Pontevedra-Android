package org.terrasdepontevedra.petra.domain.usecase.base;

import org.terrasdepontevedra.petra.util.interfaces.Action;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


@SuppressWarnings("unchecked")
public abstract class UseCaseBaseImpl<TUseCase extends UseCaseBase<TUseCase, TParam, TResult, TResultMap>, TRepository, TParam, TResult, TResultMap>
        implements UseCaseBase<TUseCase, TParam, TResult, TResultMap> {

    protected final TRepository mRepository;
    private Function<TResult, TResultMap> mMapFunction;
    private Action mPreExecuteAction;
    private Action mPostExecuteAction;
    private Action1<Throwable> mThrowableAction1;

    protected TParam mParam;

    protected UseCaseBaseImpl(TRepository tRepository) {
        mRepository = tRepository;
    }

    @Override
    public UseCaseBase<TUseCase, TParam, TResult, TResultMap> map(Function<TResult, TResultMap> tResultTResultMapFunction) {
        mMapFunction = tResultTResultMapFunction;
        return this;
    }

    @Override
    public TUseCase setPreAndPostActions(Action preAction, Action postAction) {
        mPreExecuteAction = preAction;
        mPostExecuteAction = postAction;

        return (TUseCase) this;
    }

    @Override
    public TUseCase setErrorAction(Action1<Throwable> throwableAction1) {
        mThrowableAction1 = throwableAction1;
        return (TUseCase) this;
    }

    @Override
    public TUseCase setParams(TParam param) {
        mParam = param;
        return (TUseCase) this;
    }

    @Override
    public void execute(Observable<TResult> tResultObservable, final Action1<TResultMap> itinerarySubscriber) {
        tryExecutePreAction();
        tResultObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(mMapFunction)
                .subscribe(new DisposableObserver<TResultMap>() {
                    @Override
                    public void onNext(@NonNull TResultMap tResultMap) {
                        tryExecutePostAction();
                        if (itinerarySubscriber != null)
                            itinerarySubscriber.execute(tResultMap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mThrowableAction1 != null)
                            mThrowableAction1.execute(e);
                    }

                    @Override
                    public void onComplete() {
                        dispose();
                    }
                });
    }

    private void tryExecutePreAction() {
        if (mPreExecuteAction != null)
            mPreExecuteAction.execute();
    }

    private void tryExecutePostAction() {
        if (mPostExecuteAction != null)
            mPostExecuteAction.execute();
    }

}
