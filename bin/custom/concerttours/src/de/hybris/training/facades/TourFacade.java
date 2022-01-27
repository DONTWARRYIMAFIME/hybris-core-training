package de.hybris.training.facades;

import de.hybris.training.data.TourData;

public interface TourFacade
{
    TourData getTourDetails(final String tourId);
}

