package com.hybris.training.service.impl;

import com.hybris.training.daos.TokenizerDAO;
import com.hybris.training.model.TokenizerModel;
import com.hybris.training.service.TokenizerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultTokenizerService implements TokenizerService
{
    @Autowired
    private TokenizerDAO tokenizerDAO;

    public void setTokenizerDAO(final TokenizerDAO tokenizerDAO)
    {
        this.tokenizerDAO = tokenizerDAO;
    }

    @Override
    public List<TokenizerModel> getTokenizers()
    {
        return tokenizerDAO.findTokenizers();
    }
}
