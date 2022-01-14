/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.hybris.training.setup;

import static com.hybris.training.constants.ChildExtensionConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.hybris.training.constants.ChildExtensionConstants;
import com.hybris.training.service.ChildExtensionService;


@SystemSetup(extension = ChildExtensionConstants.EXTENSIONNAME)
public class ChildExtensionSystemSetup
{
	private final ChildExtensionService childExtensionService;

	public ChildExtensionSystemSetup(final ChildExtensionService childExtensionService)
	{
		this.childExtensionService = childExtensionService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		childExtensionService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ChildExtensionSystemSetup.class.getResourceAsStream("/childExtension/sap-hybris-platform.png");
	}
}
