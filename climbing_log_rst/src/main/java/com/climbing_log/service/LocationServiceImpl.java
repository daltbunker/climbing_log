package com.climbing_log.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Location;
import com.climbing_log.repository.LocationRepository;
import com.climbing_log.service.ifc.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location addLocation(Location newLocation) {
        Location location = locationRepository.save(newLocation);
        return location;
    }

    @Override
    public Location getLocationById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("location id must not be null");
        }
        Optional<Location> locationOpt = locationRepository.findById(id);

        if (locationOpt.isEmpty()) {
            throw new ResourceNotFoundException("location not found");
        }
        
        Location location = locationOpt.get();
        return location;
    }

    @Override
    public Page<Location> getAllLocations(Pageable pageable) {
        Page<Location> locationPage = locationRepository.findAll(pageable);
        if (locationPage == null || locationPage.isEmpty()) {
            throw new ResourceNotFoundException("locations not found");
        }
        return locationPage;
    }
}
