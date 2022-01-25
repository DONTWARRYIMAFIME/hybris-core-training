package de.hybris.training.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.training.daos.TokenizerDAO;
import de.hybris.training.model.TokenizerModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "tokenizerDAO")
public class DefaultTokenizerDAO implements TokenizerDAO
{
    private final FlexibleSearchService flexibleSearchService;

    public DefaultTokenizerDAO(FlexibleSearchService flexibleSearchService)
    {
        this.flexibleSearchService = flexibleSearchService;
    }


    @Override
    public List<TokenizerModel> findTokenizers()
    {
        final String queryString =
                "SELECT {p:" + TokenizerModel.PK + "} " +
                        "FROM {" + TokenizerModel._TYPECODE + " AS p} ";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

        return flexibleSearchService.<TokenizerModel> search(query).getResult();
    }
}
