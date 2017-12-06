package org.terrasdepontevedra.petra.domain.usecase.base;

interface UseCaseParams<TUseCase, TParam> {
    TUseCase setParams(TParam param);

}
