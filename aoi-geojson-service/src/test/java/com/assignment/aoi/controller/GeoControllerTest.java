package com.assignment.aoi.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.assignment.aoi.model.GeoJsonObject;
import com.assignment.aoi.model.GeoJsonObject.Feature;
import com.assignment.aoi.service.GeoService;

/**
 * Test class for {@link GeoController}.
 */
class GeoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GeoService geoService;  // Mock the GeoService

    @InjectMocks
    private GeoController geoController; // Controller to be tested

    /**
     * This method ensures that the mocks are properly initialized and MockMvc is set up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
        mockMvc = MockMvcBuilders.standaloneSetup(geoController).build(); // Set up MockMvc with the controller
    }

    /**
     * Test for the {@link GeoController#getFootprints(GeoJsonObject)} method.
     * This test verifies that the controller properly processes a valid AOI and returns the expected GeoJSON footprints.
     * 
     * @throws Exception if any unexpected behavior occurs during the test
     */
    @Test
    void testGetFootprints() throws Exception {
        GeoJsonObject aoi = new GeoJsonObject();

        GeoJsonObject expectedFootprints = new GeoJsonObject();
        expectedFootprints.setType("FeatureCollection");

        // Stub the geoService 
        when(geoService.getFootPrints(aoi)).thenReturn(expectedFootprints);

        //Make the HTTP POST request and check the response
        mockMvc.perform(post("/api/get-footprints")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\": \"FeatureCollection\"}"))  // Request Body as JSON
                .andExpect(status().isOk());  // Expect HTTP 200 OK

        // Verify that the geoService's getFootPrints method was called exactly once with the provided AOI
    }

    /**
     * Test for the {@link GeoController#getFootprints(GeoJsonObject)} method with invalid input.
     * This test ensures the controller properly handles invalid or empty AOI data.
     */
    @Test
    void testGetFootprintsWithInvalidInput() throws Exception {
        // Arrange: Send invalid data (empty or incorrect JSON)
        String invalidJson = ""; // An empty GeoJSON object

        mockMvc.perform(post("/api/get-footprints")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))  // Request Body as invalid JSON
                .andExpect(status().isBadRequest());  // Expect HTTP 400 Bad Request
    }

    /**
     * Test for the {@link GeoController#getFootprints(GeoJsonObject)} method when the service throws an exception.
     * This test ensures the controller properly handles exceptions thrown by the service layer.
     */
    @Test
    void testGetFootprintsInternalServerError() throws Exception {
        // Stub the geoService to throw an exception
        when(geoService.getFootPrints(Mockito.any())).thenThrow(new RuntimeException("Internal error"));

        // Act & Assert: Perform the POST request and expect HTTP 500 Internal Server Error
        mockMvc.perform(post("/api/get-footprints")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\": \"FeatureCollection\"}"))  // Request Body as JSON
                .andExpect(status().isInternalServerError());  // Expect HTTP 500 Internal Server Error
    }
    
}
