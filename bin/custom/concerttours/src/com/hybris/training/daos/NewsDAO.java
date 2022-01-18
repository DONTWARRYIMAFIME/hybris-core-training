package com.hybris.training.daos;

import com.hybris.training.model.NewsModel;

import java.util.Date;
import java.util.List;

public interface NewsDAO
{
    List<NewsModel> getNewsOfTheDay(Date date);
}