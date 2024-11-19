package com.assignment.aoi.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assignment.aoi.model.GeoJsonObject;

class GeoRepositoryImplTest {

    private GeoRepositoryImpl geoRepository;

    @BeforeEach
    void setUp() {
        // Initialize the repository before each test
        geoRepository = new GeoRepositoryImpl();
    }

    @Test
    void testGetFootPrints() {
        // Create a mock AOI (Area of Interest)
        GeoJsonObject aoi = new GeoJsonObject();
        aoi.setType("FeatureCollection");
        aoi.setFeatures(List.of()); // Features are not used for input

        GeoJsonObject result = geoRepository.getFootPrints(aoi);

        // Assert: Validate the returned GeoJsonObject
        assertNotNull(result, "Result should not be null");
        assertEquals("FeatureCollection", result.getType(), "Type should be 'FeatureCollection'");

        // Validate the number of features
        List<GeoJsonObject.Feature> features = result.getFeatures();
        assertNotNull(features, "Features list should not be null");
        assertEquals(5, features.size(), "There should be exactly 5 features");

        // Validate each footprint's properties and geometry
        for (int i = 0; i < features.size(); i++) {
            GeoJsonObject.Feature feature = features.get(i);

            // Validate feature type
            assertEquals("Feature", feature.getType(), "Feature type should be 'Feature'");

            // Validate feature properties
            assertNotNull(feature.getProperties(), "Properties should not be null");

            // Validate geometry
            GeoJsonObject.Geometry geometry = feature.getGeometry();
            assertNotNull(geometry, "Geometry should not be null");
            assertEquals("Polygon", geometry.getType(), "Geometry type should be 'Polygon'");
            assertNotNull(geometry.getCoordinates(), "Coordinates should not be null");

            // Validate the number of coordinates for the polygon
            List<List<double[]>> coordinates = geometry.getCoordinates();
            assertEquals(5, coordinates.get(0).size(), "Polygon should have 5 points (closed)");
        }
    }
}
