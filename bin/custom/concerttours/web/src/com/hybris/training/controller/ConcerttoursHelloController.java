/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.hybris.training.controller;

import static com.hybris.training.constants.ConcerttoursConstants.PLATFORM_LOGO_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hybris.training.service.ConcerttoursService;


@Controller
public class ConcerttoursHelloController
{
	@Autowired
	private ConcerttoursService concerttoursService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model)
	{
		model.addAttribute("logoUrl", concerttoursService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "welcome";
	}
}
