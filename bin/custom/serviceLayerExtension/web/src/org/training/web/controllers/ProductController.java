package org.training.web.controllers;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ProductController implements Controller
{
    private CatalogService catalogService;
    private ProductService productService;

    public ModelAndView handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception
    {
        catalogService.setSessionCatalogVersion("hwcatalog", "Online");

        final String code = request.getParameter("code");
        ProductModel product = null;

        if (code != null)
        {
            product = productService.getProduct(code);
        }

        final Map<String, Object> model = new HashMap<>();
        model.put("product", product);

        return new ModelAndView("product.jsp", model);
    }

    public void setCatalogService(final CatalogService catalogService)
    {
        this.catalogService = catalogService;
    }

    public void setProductService(final ProductService productService)
    {
        this.productService = productService;
    }

}
