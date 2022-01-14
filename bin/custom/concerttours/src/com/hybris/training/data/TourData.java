package com.hybris.training.data;

import java.util.List;

public class TourData
{
    private String id;
    private String tourName;
    private String description;
    private List<ConcertSummaryData> concerts;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTourName()
    {
        return tourName;
    }

    public void setTourName(String tourName)
    {
        this.tourName = tourName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<ConcertSummaryData> getConcerts()
    {
        return concerts;
    }

    public void setConcerts(List<ConcertSummaryData> concerts)
    {
        this.concerts = concerts;
    }
}
