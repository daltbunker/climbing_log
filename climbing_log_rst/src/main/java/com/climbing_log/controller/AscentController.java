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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.climbing_log.model.Ascent;
import com.climbing_log.model.Climb;
import com.climbing_log.model.User;
import com.climbing_log.service.UserDetailsServiceImpl;
import com.climbing_log.service.ifc.AscentService;
import com.climbing_log.service.ifc.ClimbService;

@RestController
@RequestMapping(path = "/ascents")
public class AscentController {
    @Autowired
    AscentService ascentService;
    @Autowired
    ClimbService climbService;
    @Autowired
    UserDetailsServiceImpl userService;

    @PostMapping(path = "/add")
    public ResponseEntity<Ascent> addAscent(
            @RequestBody @Valid Ascent newAscent,
            @RequestParam(required = false, name = "user") String username,
            @RequestParam(required = false, name = "climb" ) Integer climb_id) {

        Climb climb = climbService.getClimbById(climb_id);
        newAscent.setClimb(climb);
        User user = userService.getUserByUsername(username);
        newAscent.setUser(user);
        Ascent Ascent = ascentService.addAscent(newAscent);
        return ResponseEntity.ok(Ascent);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Ascent> getAscent(
            @PathVariable(name = "id") Integer id ) {
        Ascent ascent = ascentService.getAscentById(id);
        return ResponseEntity.ok(ascent);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Page<Ascent>> getAllAscents(
        @PageableDefault(page = 0, size = 30) Pageable pageable) {
        Page<Ascent> ascents = ascentService.getAllAscents(pageable);
        return ResponseEntity.ok(ascents);
    }
}
