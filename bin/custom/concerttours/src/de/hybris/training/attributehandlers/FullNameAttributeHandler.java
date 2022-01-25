package de.hybris.training.attributehandlers;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.training.model.UserDataModel;

public class FullNameAttributeHandler implements DynamicAttributeHandler<String, UserDataModel>
{
    public static final String VALUE_DELIMITER = " ";

    @Override
    public String get(final UserDataModel item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException("Item model is required");
        }
        return item.getFirstName() + VALUE_DELIMITER + item.getLastName();
    }

    @Override
    public void set(final UserDataModel item, final String value)
    {
        if (item != null && value != null)
        {
            final String[] split = value.split(VALUE_DELIMITER);
            item.setFirstName(split[0]);
            item.setLastName(split[1]);
        }
    }
}
