/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.training.service;

public interface ConcerttoursService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
