/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.setup;

import static org.training.constants.ServiceLayerExtensionConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.training.constants.ServiceLayerExtensionConstants;
import org.training.service.ServiceLayerExtensionService;


@SystemSetup(extension = ServiceLayerExtensionConstants.EXTENSIONNAME)
public class ServiceLayerExtensionSystemSetup
{
	private final ServiceLayerExtensionService serviceLayerExtensionService;

	public ServiceLayerExtensionSystemSetup(final ServiceLayerExtensionService serviceLayerExtensionService)
	{
		this.serviceLayerExtensionService = serviceLayerExtensionService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		serviceLayerExtensionService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ServiceLayerExtensionSystemSetup.class.getResourceAsStream("/serviceLayerExtension/sap-hybris-platform.png");
	}
}
