package com.climbing_log.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.climbing_log.model.Location;
import com.climbing_log.service.ifc.LocationService;

@RestController
@RequestMapping(path = "/api/locations")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping(path = "/add")
    public ResponseEntity<Location> addLocation(
            @RequestBody @Valid Location newLocation) {
        Location location = locationService.addLocation(newLocation);
        return ResponseEntity.ok(location);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Location> getLocation(
            @PathVariable(name = "id") Integer id) {
        Location location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Page<Location>> getStudents(
            @PageableDefault(page = 0, size = 30) Pageable pageable) {
        Page<Location> locations = locationService.getAllLocations(pageable);
        return ResponseEntity.ok(locations);
    }
}
