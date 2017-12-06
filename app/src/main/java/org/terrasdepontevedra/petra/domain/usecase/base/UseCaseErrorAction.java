package org.terrasdepontevedra.petra.domain.usecase.base;

import org.terrasdepontevedra.petra.util.interfaces.Action1;

interface UseCaseErrorAction<TUseCase> {

    TUseCase setErrorAction(Action1<Throwable> throwableAction1);
}
