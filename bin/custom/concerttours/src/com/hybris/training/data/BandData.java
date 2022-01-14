package com.hybris.training.data;

import java.util.List;

public class BandData
{
    private String id;
    private String name;
    private Long albumsSold;
    private String description;
    private List<String> genres;
    private List<TourSummaryData> tours;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getAlbumsSold()
    {
        return albumsSold;
    }

    public void setAlbumsSold(Long albumsSold)
    {
        this.albumsSold = albumsSold;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<String> getGenres()
    {
        return genres;
    }

    public void setGenres(List<String> genres)
    {
        this.genres = genres;
    }

    public List<TourSummaryData> getTours()
    {
        return tours;
    }

    public void setTours(List<TourSummaryData> tours)
    {
        this.tours = tours;
    }
}
