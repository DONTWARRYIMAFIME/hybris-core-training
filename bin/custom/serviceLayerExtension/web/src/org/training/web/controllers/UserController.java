/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.web.controllers;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.core.model.user.UserModel;

public class UserController implements Controller
{
    private UserService userService;

    public ModelAndView handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception
    {
        final String uid = request.getParameter("uid");
        UserModel user = null;

        if (uid == null)
        {
            user = userService.getCurrentUser();
        }
        else
        {
            user = userService.getUser(uid);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        return new ModelAndView("user.jsp", model);
    }

    @Required
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

}