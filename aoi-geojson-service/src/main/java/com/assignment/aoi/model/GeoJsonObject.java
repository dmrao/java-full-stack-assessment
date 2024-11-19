package com.assignment.aoi.model;

import java.util.List;

public class GeoJsonObject {

    private String type; // e.g., "FeatureCollection" or "Polygon"
    private List<Feature> features;

    // Constructors
    public GeoJsonObject() {
    }

    public GeoJsonObject(String type, List<Feature> features) {
        this.type = type;
        this.features = features;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    // Inner class representing a GeoJSON Feature
    public static class Feature {
        private String type; // e.g., "Feature"
        private Geometry geometry;
        private Properties properties;

        // Constructors
        public Feature() {
        }

        public Feature(String type, Geometry geometry, Properties properties) {
            this.type = type;
            this.geometry = geometry;
            this.properties = properties;
        }

        // Getters and Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public Properties getProperties() {
            return properties;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }
    }

    // Inner class representing a GeoJSON Geometry
    public static class Geometry {
        private String type; // e.g., "Polygon", "Point"
        private List<List<double[]>> coordinates;

        // Constructors
        public Geometry() {
        }

        public Geometry(String type, List<List<double[]>> coordinates) {
            this.type = type;
            this.coordinates = coordinates;
        }

        // Getters and Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<List<double[]>> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<List<double[]>> coordinates) {
            this.coordinates = coordinates;
        }
    }

    // Inner class representing GeoJSON Properties (optional metadata)
    public static class Properties {
        private String name;

        // Constructors
        public Properties() {
        }

        public Properties(String name) {
            this.name = name;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
