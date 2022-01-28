package de.hybris.training.controller;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.training.data.TourData;
import de.hybris.training.facades.TourFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequestMapping("/tours")
@Controller
public class TourController
{
    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";
    private CatalogVersionService catalogVersionService;
    private TourFacade tourFacade;

    @GetMapping("/{tourId}")
    public String showTourDetails(@PathVariable final String tourId, final Model model)
    {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);

        final String decodedTourId = URLDecoder.decode(tourId, StandardCharsets.UTF_8);
        final TourData tour = tourFacade.getTourDetails(decodedTourId);

        model.addAttribute("tour", tour);
        return "TourDetails";
    }

    @Autowired
    public void setCatalogVersionService(final CatalogVersionService catalogVersionServiceService)
    {
        this.catalogVersionService = catalogVersionServiceService;
    }

    @Autowired
    public void setFacade(final TourFacade facade)
    {
        this.tourFacade = facade;
    }
}