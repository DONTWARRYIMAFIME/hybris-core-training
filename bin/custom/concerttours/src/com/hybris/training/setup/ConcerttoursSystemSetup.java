/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.hybris.training.setup;

import static com.hybris.training.constants.ConcerttoursConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.hybris.training.constants.ConcerttoursConstants;
import com.hybris.training.service.ConcerttoursService;


@SystemSetup(extension = ConcerttoursConstants.EXTENSIONNAME)
public class ConcerttoursSystemSetup
{
	private final ConcerttoursService concerttoursService;

	public ConcerttoursSystemSetup(final ConcerttoursService concerttoursService)
	{
		this.concerttoursService = concerttoursService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		concerttoursService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ConcerttoursSystemSetup.class.getResourceAsStream("/concerttours/sap-hybris-platform.png");
	}
}
