package com.hybris.training.daos;

import com.hybris.training.model.TokenizerModel;

import java.util.List;

public interface TokenizerDAO
{
    List<TokenizerModel> findTokenizers();
}
