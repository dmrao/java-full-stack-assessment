package com.assignment.aoi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.aoi.model.GeoJsonObject;
import com.assignment.aoi.service.GeoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api")
@Tag(name = "GeoJSON API", description = "API to process GeoJSON Areas of Interest (AoI) and generate footprints")
public class GeoController {

    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @PostMapping("/get-footprints")
    @Operation(
        summary = "Generate footprints for a GeoJSON Area of Interest",
        description = "Accepts a GeoJSON object representing an AoI and returns a GeoJSON object with generated footprints.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "GeoJSON object representing the Area of Interest (AoI)",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = GeoJsonObject.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully processed AoI and generated footprints",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeoJsonObject.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid input data"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        }
    )
    public ResponseEntity<GeoJsonObject> getFootprints(@RequestBody GeoJsonObject aoi) {
    	// TODO : Add more validation
    	if (aoi == null ) {
            // Return HTTP 400 Bad Request if AoI is null or empty
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Attempt to get footprints from the service
            GeoJsonObject footprints = geoService.getFootPrints(aoi);
            return ResponseEntity.ok(footprints);
        } catch (Exception e) {
            // Log the exception (optional) and return HTTP 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
