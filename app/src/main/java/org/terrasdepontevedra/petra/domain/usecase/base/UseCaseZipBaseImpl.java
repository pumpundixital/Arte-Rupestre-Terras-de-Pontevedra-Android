package org.terrasdepontevedra.petra.domain.usecase.base;

import org.terrasdepontevedra.petra.util.interfaces.Action;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


@SuppressWarnings("unchecked")
public abstract class UseCaseZipBaseImpl<TUseCase extends UseCaseBaseZip<TUseCase, TParam, TResult, TResultZip, TResultMap>, TRepository, TParam, TResult, TResultZip, TResultMap>
        implements UseCaseBaseZip<TUseCase, TParam, TResult, TResultZip, TResultMap> {

    protected final TRepository mRepository;
    private BiFunction<TResult, TResultZip, TResultMap> mMapFunction;
    private Action mPreExecuteAction;
    private Action mPostExecuteAction;
    private Action1<Throwable> mThrowableAction1;
    protected TParam mParams;

    protected UseCaseZipBaseImpl(TRepository tRepository) {
        mRepository = tRepository;
    }

    @Override
    public TUseCase map(BiFunction<TResult, TResultZip, TResultMap> tResultTResultMapFunction) {
        mMapFunction = tResultTResultMapFunction;
        return (TUseCase) this;
    }

    @Override
    public TUseCase setParams(TParam params) {
        mParams = params;
        return (TUseCase) this;
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
    public void execute(Observable<TResult> tResultObservable, Observable<TResultZip> tResultObservableZip, Action1<TResultMap> itinerarySubscriber) {
        tryExecutePreAction();

        Observable
                .zip(tResultObservable, tResultObservableZip, mMapFunction)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new DisposableObserver<TResultMap>() {
                    @Override
                    public void onNext(@NonNull TResultMap tResultMap) {
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
                        tryExecutePostAction();
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
