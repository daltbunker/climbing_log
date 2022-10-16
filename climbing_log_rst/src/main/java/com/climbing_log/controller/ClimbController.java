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

import com.climbing_log.model.Climb;
import com.climbing_log.model.Location;
import com.climbing_log.service.ifc.ClimbService;
import com.climbing_log.service.ifc.LocationService;

@RestController
@RequestMapping(path = "/api/climbs")
public class ClimbController {
    @Autowired
    ClimbService climbService;
    @Autowired
    LocationService locationService;

    @PostMapping(path = "/add/{location_id}") // TODO change all path variables to query params
    public ResponseEntity<Climb> addClimb(
            @RequestBody @Valid Climb newClimb,
            @PathVariable(name = "location_id") Integer location_id) {

        Location location = locationService.getLocationById(location_id);
        newClimb.setLocation(location);
        Climb climb = climbService.addClimb(newClimb);
        return ResponseEntity.ok(climb);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Climb> getClimb(
            @PathVariable(name = "id") Integer id ) {
        Climb climb = climbService.getClimbById(id);
        return ResponseEntity.ok(climb);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Page<Climb>> getAllClimbs(
        @PageableDefault(page = 0, size = 30) Pageable pageable) {
            Page<Climb> climbs = climbService.getAllClimbs(pageable);
            return ResponseEntity.ok(climbs);
    }

}
