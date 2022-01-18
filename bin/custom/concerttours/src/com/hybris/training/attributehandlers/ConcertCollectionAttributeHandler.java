package com.hybris.training.attributehandlers;

import com.hybris.training.model.ConcertModel;
import com.hybris.training.model.ItemWithAllAttributesModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConcertCollectionAttributeHandler extends AbstractDynamicAttributeHandler<List<ConcertModel>, ItemWithAllAttributesModel>
{
    @Override
    public List<ConcertModel> get(final ItemWithAllAttributesModel model)
    {
        if (model.getConcertCollection().isEmpty())
        {
            ConcertModel concert = new ConcertModel();
            concert.setCode("Dynamic added");
            model.getConcertCollection().add(concert);
        }

        return (List<ConcertModel>) model.getConcertCollection();
    }
}