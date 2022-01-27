package de.hybris.training.interceptors;

import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.training.events.BandAlbumSalesEvent;
import de.hybris.training.model.BandModel;
import org.springframework.beans.factory.annotation.Autowired;

import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

public class BandAlbumSalesInterceptor implements ValidateInterceptor, PrepareInterceptor
{
    private static final long BIG_SALES = 50000L;
    private static final long NEGATIVE_SALES = 0L;

    @Autowired
    private EventService eventService;

    @Override
    public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
    {
        if (model instanceof BandModel)
        {
            final BandModel band = (BandModel) model;
            final Long sales = band.getAlbumSales();

            if (sales != null && sales < NEGATIVE_SALES)
            {
                throw new InterceptorException("Album sales must be positive");
            }
        }
    }

    @Override
    public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
    {
        if (model instanceof BandModel)
        {
            final BandModel band = (BandModel) model;

            if (hasBecomeBig(band, ctx))
            {
                eventService.publishEvent(new BandAlbumSalesEvent(band.getCode(), band.getName(), band.getAlbumSales()));
            }
        }
    }
    private boolean hasBecomeBig(final BandModel band, final InterceptorContext ctx)
    {
        final Long sales = band.getAlbumSales();

        if (sales != null && sales >= BIG_SALES)
        {
            if (ctx.isNew(band))
            {
                return true;
            }
            else
            {
                final Long oldValue = getItemModelContext(band).getOriginalValue(BandModel.ALBUMSALES);

                if (oldValue == null || oldValue.intValue() < BIG_SALES)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
