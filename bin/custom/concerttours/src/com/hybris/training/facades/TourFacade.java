package com.hybris.training.facades;

import com.hybris.training.data.TourData;

public interface TourFacade
{
    TourData getTourDetails(final String tourId);
}
