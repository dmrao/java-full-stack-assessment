package com.assignment.aoi.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.assignment.aoi.model.GeoJsonObject;

/**
 * Implementation of the GeoRepository interface.
 * Provides functionality to generate GeoJSON footprints
 * within a given Area of Interest (AOI).
 */
@Repository
public class GeoRepositoryImpl implements GeoRepository {

    /**
     * Generates a GeoJsonObject representing footprints within the given AOI.
     * 
     * @param aoi The Area of Interest (AOI) as a GeoJsonObject.
     * @return A GeoJsonObject containing a collection of footprints (polygons)
     *         generated within the AOI.
     */
    @Override
    public GeoJsonObject getFootPrints(GeoJsonObject aoi) {
        // Create a new GeoJsonObject to represent the footprints
        GeoJsonObject geoJsonObject = new GeoJsonObject();
        geoJsonObject.setType("FeatureCollection"); // Set the GeoJSON type as a collection of features

        // Initialize a list to hold the features (footprints)
        List<GeoJsonObject.Feature> features = new ArrayList<>();

        // Generate 5 footprints, each with a slight offset
        for (int i = 1; i <= 5; i++) {
            // Create a new feature to represent a footprint
            GeoJsonObject.Feature feature = new GeoJsonObject.Feature();
            feature.setType("Feature"); // Set the feature type

            // Add properties to the feature
            GeoJsonObject.Properties properties = new GeoJsonObject.Properties();
            properties.setName("Footprint " + i); // Assign a unique name to each footprint
            feature.setProperties(properties); // Attach properties to the feature

            // Create the geometry for the feature (a polygon)
            GeoJsonObject.Geometry geometry = new GeoJsonObject.Geometry();
            geometry.setType("Polygon"); // Set the geometry type as Polygon

            // Define base latitude and longitude for the Westminster area
            double baseLat = 51.4975; // Approximate latitude of Westminster
            double baseLng = -0.1357; // Approximate longitude of Westminster
            double offset = 0.001 * i; // Incremental offset to distinguish footprints

            // Build the coordinates for the polygon
            List<List<double[]>> coordinates = new ArrayList<>(); // Outer list for multiple polygons
            List<double[]> polygon = new ArrayList<>(); // Inner list for a single polygon

            // Define the corners of the polygon
            polygon.add(new double[] { baseLng, baseLat }); // Bottom-left corner
            polygon.add(new double[] { baseLng + offset, baseLat }); // Bottom-right corner
            polygon.add(new double[] { baseLng + offset, baseLat + offset }); // Top-right corner
            polygon.add(new double[] { baseLng, baseLat + offset }); // Top-left corner
            polygon.add(new double[] { baseLng, baseLat }); // Closing the polygon by repeating the first point

            // Add the polygon to the coordinates list
            coordinates.add(polygon);

            // Attach the coordinates to the geometry
            geometry.setCoordinates(coordinates);
            feature.setGeometry(geometry); // Attach geometry to the feature

            // Add the feature to the list of features
            features.add(feature);
        }

        // Attach the list of features to the GeoJSON object
        geoJsonObject.setFeatures(features);

        // Return the generated GeoJSON object
        return geoJsonObject;
    }
}
