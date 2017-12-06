package org.terrasdepontevedra.petra.domain.usecase.base;


import org.terrasdepontevedra.petra.util.interfaces.Action1;

public interface UseCaseExecute<TResult> {
    void execute(Action1<TResult> tResultMapSubscriber);
}
