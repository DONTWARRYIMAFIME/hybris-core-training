/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.controller;

import static org.training.constants.ServiceLayerExtensionConstants.PLATFORM_LOGO_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.training.service.ServiceLayerExtensionService;


@Controller
public class ServiceLayerExtensionHelloController
{
	@Autowired
	private ServiceLayerExtensionService serviceLayerExtensionService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model)
	{
		model.addAttribute("logoUrl", serviceLayerExtensionService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "welcome";
	}
}
