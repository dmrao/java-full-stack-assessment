package com.assignment.aoi.repository;

import com.assignment.aoi.model.GeoJsonObject;

/**
 * Interface representing a repository for interacting with Area of Interest (AOI) and footprint data.
 */
public interface GeoRepository {
    
    /**
     * Retrieves footprints within the given Area of Interest (AOI) in the form of a GeoJSON object.
     * The method generates and returns a collection of footprint features that fit within the specified AOI.
     * 
     * @param aoi The Area of Interest (AOI) represented as a GeoJsonObject
     * @return A GeoJsonObject containing a list of footprint features within the specified AOI.
     */
    public GeoJsonObject getFootPrints(GeoJsonObject aoi);
}
