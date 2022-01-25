package de.hybris.training.service;

import de.hybris.training.model.NewsModel;

import java.util.Date;
import java.util.List;

public interface NewsService
{
    List<NewsModel> getNewsOfTheDay(Date date);
}
