package de.hybris.training.controller;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.training.data.BandData;
import de.hybris.training.facades.BandFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequestMapping("/bands")
@Controller
public class BandController
{
    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";
    private CatalogVersionService catalogVersionService;
    private BandFacade bandFacade;

    @GetMapping
    public String showBands(final Model model)
    {
        final List<BandData> bands = bandFacade.getBands();
        model.addAttribute("bands", bands);
        return "BandList";
    }

    @GetMapping( "/{bandId}")
    public String showBandDetails(@PathVariable final String bandId, final Model model)
    {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);

        final String decodedBandId = URLDecoder.decode(bandId, StandardCharsets.UTF_8);
        final BandData band = bandFacade.getBand(decodedBandId);

        model.addAttribute("band", band);
        return "BandDetails";
    }

    @Autowired
    public void setCatalogVersionService(final CatalogVersionService catalogVersionServiceService)
    {
        this.catalogVersionService = catalogVersionServiceService;
    }

    @Autowired
    public void setFacade(final BandFacade facade)
    {
        this.bandFacade = facade;
    }
}