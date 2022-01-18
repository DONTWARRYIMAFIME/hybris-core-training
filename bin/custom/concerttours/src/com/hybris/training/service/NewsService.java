package com.hybris.training.service;

import com.hybris.training.model.NewsModel;

import java.util.Date;
import java.util.List;

public interface NewsService
{
    List<NewsModel> getNewsOfTheDay(Date date);
}
