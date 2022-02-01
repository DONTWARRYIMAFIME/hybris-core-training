package de.hybris.training.facades.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.variants.model.VariantProductModel;
import de.hybris.training.data.BandData;
import de.hybris.training.facades.BandFacade;
import de.hybris.training.model.BandModel;
import de.hybris.training.service.BandService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static de.hybris.platform.testframework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This test file tests and demonstrates the behavior of the BandFacade's methods getAllBands and getBand.
 */
@IntegrationTest
public class DefaultBandFacadeIntegrationTest extends ServicelayerTransactionalTest
{
    @Resource
    private BandFacade bandFacade;
    @Resource
    private BandService bandService;
    @Resource
    private ModelService modelService;
    /**
     * Test band
     */
    private BandModel bandModel;
    /**
     * Name of test band.
     */
    private static final String BAND_CODE = "101-JAZ";
    /**
     * Name of test band.
     */
    private static final String BAND_NAME = "Tight Notes";
    /**
     * History of test band.
     */
    private static final String BAND_HISTORY = "New contemporary, 7-piece Jaz unit from London, formed in 2015";
    /**
     * Albums sold by test band.
     */
    private static final Long ALBUMS_SOLD = 10L;

    @Before
    public void setUp() throws Exception
    {
        try
        {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        catch (InterruptedException exc)
        {
        }
        importCsv("/impex/essentialdata-mediaformats.impex", "UTF-8");

        // This instance of a BandModel will be used by the tests
        bandModel = modelService.create(BandModel.class);
        bandModel.setCode(BAND_CODE);
        bandModel.setName(BAND_NAME);

        bandModel.setHistory(BAND_HISTORY);
        bandModel.setAlbumSales(ALBUMS_SOLD);
    }

    /**
     * Tests exception behavior by getting a band which doesn't exist
     */
    @Test(expected = UnknownIdentifierException.class)
    public void testInvalidParameter()
    {
        bandFacade.getBand(BAND_NAME);
    }

    /**
     * Tests exception behavior by passing in a null parameter
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter()
    {
        bandFacade.getBand(null);
    }

    /**
     * Tests and demonstrates the Facade's methods
     */
    @Test
    public void testBandFacade()
    {
        List<BandData> bandListData = bandFacade.getBands();
        assertNotNull(bandListData);

        final int size = bandListData.size();

        modelService.save(bandModel);
        bandListData = bandFacade.getBands();
        assertNotNull(bandListData);
        assertEquals(size + 1, bandListData.size());
        assertEquals(BAND_CODE, bandListData.get(size).getId());
        assertEquals(BAND_NAME, bandListData.get(size).getName());
        assertEquals(ALBUMS_SOLD, bandListData.get(size).getAlbumsSold());
        assertEquals(BAND_HISTORY, bandListData.get(size).getDescription());
        final BandData persistedBandData = bandFacade.getBand(BAND_CODE);

        assertNotNull(persistedBandData);
        assertEquals(BAND_CODE, persistedBandData.getId());
        assertEquals(BAND_NAME, persistedBandData.getName());
        assertEquals(ALBUMS_SOLD, persistedBandData.getAlbumsSold());
        assertEquals(BAND_HISTORY, persistedBandData.getDescription());
    }

    @Test
    public void testBandServiceTours() throws Exception
    {
        createCoreData();
        importCsv("/impex/essentialdata-mediaformats.impex", "UTF-8");
        importCsv("/impex/concerttours-bands.impex", "utf-8");
        final BandModel band = bandService.getBandForCode("A001");
        assertNotNull("No band found", band);
        final Set<ProductModel> tours = band.getTours();
        assertNotNull("No tour found", tours);
        Assert.assertEquals("not found one tour", 1, tours.size());
        final Object[] objects = new Object[5];
        final Collection<VariantProductModel> concerts = ((ProductModel) tours.toArray(objects)[0]).getVariants();
        assertNotNull("No tour found", tours);
        Assert.assertEquals("not found one tour", 6, concerts.size());
    }

    @After
    public void teardown()
    {
    }
}
