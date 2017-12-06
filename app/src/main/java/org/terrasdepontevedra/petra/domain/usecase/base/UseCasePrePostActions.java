package org.terrasdepontevedra.petra.domain.usecase.base;

import org.terrasdepontevedra.petra.util.interfaces.Action;

interface UseCasePrePostActions<TUseCase> {

    TUseCase setPreAndPostActions(Action preAction, Action postAction);


}
