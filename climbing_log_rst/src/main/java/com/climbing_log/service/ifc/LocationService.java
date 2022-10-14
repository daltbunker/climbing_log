package com.climbing_log.service.ifc;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.climbing_log.model.Location;

public interface LocationService {
    Location addLocation(Location newLocation);

    Location getLocationById(Integer id);

    Page<Location> getAllLocations(Pageable pageable);
}
