package org.terrasdepontevedra.petra.domain.mapper;

import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.domain.model.PlaceIdentity;
import org.terrasdepontevedra.petra.domain.model.Score;
import org.terrasdepontevedra.petra.domain.model.ScoreCollection;
import org.terrasdepontevedra.petra.domain.model.base.Title;

import java.util.List;

public class PlaceDtoToScore {
    public static ScoreCollection map(List<PlaceDto> placeDtos) {
        ScoreCollection scoreCollection = new ScoreCollection();
        for (PlaceDto placeDto : placeDtos) {
            scoreCollection.add(map(placeDto));
        }
        return scoreCollection;
    }

    private static Score map(PlaceDto placeDto) {
        return new Score(Title.from(placeDto.getTitle().getRendered()), false, PlaceIdentity.from(placeDto.getSlug().hashCode()));
    }
}
