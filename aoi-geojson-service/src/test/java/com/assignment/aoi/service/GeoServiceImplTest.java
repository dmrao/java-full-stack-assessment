package com.assignment.aoi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.assignment.aoi.model.GeoJsonObject;
import com.assignment.aoi.repository.GeoRepository;

/**
 * Test class for {@link GeoServiceImpl}.
 */
class GeoServiceImplTest {

    @Mock
    private GeoRepository geoRepository; 

    @InjectMocks
    private GeoServiceImpl geoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }

    /**
     * Test for the {@link GeoServiceImpl#getFootPrints(GeoJsonObject)} method.
     * This test ensures that the service correctly calls the repository method and returns the result.
     */
    @Test
    void testGetFootPrints() {
        GeoJsonObject aoi = new GeoJsonObject();
        aoi.setType("FeatureCollection");

        // Create a mocked GeoJsonObject for the expected result
        GeoJsonObject expectedFootprints = new GeoJsonObject();
        expectedFootprints.setType("FeatureCollection");

        // Stub the repository method to return the mocked footprints
        when(geoRepository.getFootPrints(aoi)).thenReturn(expectedFootprints);

        GeoJsonObject result = geoService.getFootPrints(aoi);

        // Assert: Validate that the service returns the expected result
        assertNotNull(result, "Result should not be null");
        assertEquals("FeatureCollection", result.getType(), "GeoJSON type should be 'FeatureCollection'");
        verify(geoRepository, times(1)).getFootPrints(aoi); // Verify that the repository method was called once
    }

    /**
     * Test for the {@link GeoServiceImpl#getFootPrints(GeoJsonObject)} method when the repository returns null.
     * This test ensures that the service can handle null responses from the repository correctly.
     */
    @Test
    void testGetFootPrintsWhenRepositoryReturnsNull() {
        GeoJsonObject aoi = new GeoJsonObject();
        aoi.setType("FeatureCollection");

        // Stub the repository method to return null
        when(geoRepository.getFootPrints(aoi)).thenReturn(null);

        GeoJsonObject result = geoService.getFootPrints(aoi);

        // Assert: Validate that the result is null
        assertNull(result, "Result should be null when the repository returns null");
        verify(geoRepository, times(1)).getFootPrints(aoi); // Verify that the repository method was called once
    }
}
