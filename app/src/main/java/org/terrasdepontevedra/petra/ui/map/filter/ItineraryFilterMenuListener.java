package org.terrasdepontevedra.petra.ui.map.filter;

import org.terrasdepontevedra.petra.domain.model.Itinerary;

interface ItineraryFilterMenuListener {
    void close();

    void select(Itinerary itinerary);
}
