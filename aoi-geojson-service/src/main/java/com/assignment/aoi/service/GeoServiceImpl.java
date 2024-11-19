package com.assignment.aoi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.aoi.model.GeoJsonObject;
import com.assignment.aoi.repository.GeoRepository;

@Service
public class GeoServiceImpl implements GeoService {
	
	GeoRepository geoRepository;
	
	@Autowired
	public GeoServiceImpl(GeoRepository geoRepository) {
		this.geoRepository = geoRepository;
	}
	
	public GeoJsonObject getFootPrints(GeoJsonObject aoi) {
		return geoRepository.getFootPrints(aoi);
	}

}
