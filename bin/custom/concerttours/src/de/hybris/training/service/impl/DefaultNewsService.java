package de.hybris.training.service.impl;

import de.hybris.training.daos.NewsDAO;
import de.hybris.training.model.NewsModel;
import de.hybris.training.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class DefaultNewsService implements NewsService
{
    @Autowired
    private NewsDAO newsDAO;

    public void setNewsDAO(final NewsDAO newsDAO)
    {
        this.newsDAO = newsDAO;
    }

    @Override
    public List<NewsModel> getNewsOfTheDay(final Date date)
    {
        return newsDAO.getNewsOfTheDay(date);
    }
}