package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.PetraRepository;
import org.terrasdepontevedra.petra.data.model.center.CenterDto;
import org.terrasdepontevedra.petra.domain.mapper.CenterDtoToCenter;
import org.terrasdepontevedra.petra.domain.model.CenterCollection;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBaseImpl;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import java.util.List;

public class GetCentersUseCaseImpl
        extends UseCaseBaseImpl<GetCentersUseCase, PetraRepository, String, List<CenterDto>, CenterCollection>
        implements GetCentersUseCase {
    public GetCentersUseCaseImpl(PetraRepository petraRepository) {
        super(petraRepository);
    }

    @Override
    public void execute(Action1<CenterCollection> tResultMapSubscriber) {
        super
                .map(CenterDtoToCenter::map)
                .execute(mRepository.getCenter(mParam, 100), tResultMapSubscriber);
    }
}
