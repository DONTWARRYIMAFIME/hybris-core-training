package de.hybris.training.events;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.training.model.NewsModel;

import java.util.Date;

public class BandAlbumSalesEventListener extends AbstractEventListener<BandAlbumSalesEvent>
{
    private static final String BAND_SALES_HEADLINE = "%s album sales exceed 50000";
    private static final String BAND_SALES_CONTENT = "%s album sales reported as %d";
    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String VERSION_NAME = "Online";

    private ModelService modelService;
    private CatalogVersionService catalogVersionService;

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    public CatalogVersionService getCatalogVersionService()
    {
        return catalogVersionService;
    }

    public void setCatalogVersionService(CatalogVersionService catalogVersionService)
    {
        this.catalogVersionService = catalogVersionService;
    }

    @Override
    protected void onEvent(final BandAlbumSalesEvent event)
    {
        if (event != null)
        {
            final String headline = String.format(BAND_SALES_HEADLINE, event.getName());
            final String content = String.format(BAND_SALES_CONTENT, event.getName(), event.getSales());
            final NewsModel news = modelService.create(NewsModel.class);
            final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(CATALOG_ID, VERSION_NAME);

            news.setDate(new Date());
            news.setHeadline(headline);
            news.setContent(content);
            news.setCatalogVersion(catalogVersion);

            modelService.save(news);
        }
    }
}