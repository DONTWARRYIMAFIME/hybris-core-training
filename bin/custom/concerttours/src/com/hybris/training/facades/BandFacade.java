package com.hybris.training.facades;

import com.hybris.training.data.BandData;

import java.util.List;

public interface BandFacade
{
    BandData getBand(String name);
    List<BandData> getBands();
}
