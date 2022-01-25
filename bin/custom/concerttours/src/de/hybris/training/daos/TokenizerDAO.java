package de.hybris.training.daos;

import de.hybris.training.model.TokenizerModel;

import java.util.List;

public interface TokenizerDAO
{
    List<TokenizerModel> findTokenizers();
}
