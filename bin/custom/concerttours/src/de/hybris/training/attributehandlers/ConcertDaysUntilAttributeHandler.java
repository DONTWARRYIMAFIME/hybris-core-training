package de.hybris.training.attributehandlers;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import de.hybris.training.model.ConcertModel;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ConcertDaysUntilAttributeHandler extends AbstractDynamicAttributeHandler<Long, ConcertModel>
{
    @Override
    public Long get(final ConcertModel model)
    {
        if (model.getDate() == null)
        {
            return null;
        }

        final ZonedDateTime concertDate = model.getDate().toInstant().atZone(ZoneId.systemDefault());
        final ZonedDateTime now = ZonedDateTime.now();

        if (concertDate.isBefore(now))
        {
            return 0L;
        }

        final Duration duration = Duration.between(now, concertDate);
        return duration.toDays();
    }
}