package de.hybris.training.events;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.training.model.BandModel;
import de.hybris.training.model.NewsModel;

import java.util.Date;

public class NewBandEventListener extends AbstractEventListener<AfterItemCreationEvent>
{
    private static final String NEW_BAND_HEADLINE = "New band, %s";
    private static final String NEW_BAND_CONTENT = "There is a new band in town called, %s. Tour news to be announced soon.";
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
    protected void onEvent(final AfterItemCreationEvent event)
    {
        if (event != null && event.getSource() != null)
        {
            final Object object = modelService.get(event.getSource());
            if (object instanceof BandModel)
            {
                final BandModel band = (BandModel) object;
                final String headline = String.format(NEW_BAND_HEADLINE, band.getName());
                final String content = String.format(NEW_BAND_CONTENT, band.getName());
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

}