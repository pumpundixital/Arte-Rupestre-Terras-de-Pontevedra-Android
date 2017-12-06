package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.model.center.CenterDto;
import org.terrasdepontevedra.petra.domain.model.CenterCollection;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBase;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseExecute;

import java.util.List;



public interface GetCentersUseCase
        extends UseCaseBase<GetCentersUseCase, String, List<CenterDto>, CenterCollection>,
        UseCaseExecute<CenterCollection>{
}
