package de.hybris.training.daos;

import de.hybris.training.model.NewsModel;

import java.util.Date;
import java.util.List;

public interface NewsDAO
{
    List<NewsModel> getNewsOfTheDay(Date date);
}
