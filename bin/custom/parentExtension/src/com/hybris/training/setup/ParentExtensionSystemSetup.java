/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.hybris.training.setup;

import static com.hybris.training.constants.ParentExtensionConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.hybris.training.constants.ParentExtensionConstants;
import com.hybris.training.service.ParentExtensionService;


@SystemSetup(extension = ParentExtensionConstants.EXTENSIONNAME)
public class ParentExtensionSystemSetup
{
	private final ParentExtensionService parentExtensionService;

	public ParentExtensionSystemSetup(final ParentExtensionService parentExtensionService)
	{
		this.parentExtensionService = parentExtensionService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		parentExtensionService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ParentExtensionSystemSetup.class.getResourceAsStream("/parentExtension/sap-hybris-platform.png");
	}
}
