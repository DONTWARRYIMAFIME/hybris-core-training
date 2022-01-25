package de.hybris.training.facades;

import de.hybris.training.data.BandData;

import java.util.List;

public interface BandFacade
{
    BandData getBand(String name);
    List<BandData> getBands();
}
