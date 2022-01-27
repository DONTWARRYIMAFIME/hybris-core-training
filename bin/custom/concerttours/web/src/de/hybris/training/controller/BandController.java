package de.hybris.training.controller;

import de.hybris.training.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/bands")
public class BandController
{
    @Autowired
    private BandService bandService;

    @GetMapping()
    public String printAllBands(final ModelMap model)
    {
        model.addAttribute("bands", bandService.getBands());
        return "bandList";
    }

}