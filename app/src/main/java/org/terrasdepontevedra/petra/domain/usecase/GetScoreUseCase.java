package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.domain.model.ScoreCollection;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBase;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseExecute;

import java.util.List;

public interface GetScoreUseCase
        extends UseCaseBase<GetScoreUseCase, String, List<PlaceDto>, ScoreCollection>,
        UseCaseExecute<ScoreCollection> {
}
