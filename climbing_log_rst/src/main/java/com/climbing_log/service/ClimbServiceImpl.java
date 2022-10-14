package com.climbing_log.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.climbing_log.enums.ClimbType;
import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Climb;
import com.climbing_log.repository.ClimbRepository;
import com.climbing_log.service.ifc.ClimbService;

@Service
public class ClimbServiceImpl implements ClimbService {
    @Autowired
    private ClimbRepository climbRepository;

    @Override
    public Climb addClimb(Climb newClimb) {
        if (newClimb.getGrade().isRoute() && newClimb.getType() == ClimbType.BOULDER) {
	        throw new IllegalArgumentException("climbe type and grade don't match");
	    }

        Climb climb = climbRepository.save(newClimb);
        return climb;
    }

    @Override
    public Climb getClimbById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("climb id must not be null");
        }
        Optional<Climb> climbOpt = climbRepository.findById(id);

        if (climbOpt.isEmpty()) {
            throw new ResourceNotFoundException("climb not found");
        }

        Climb climb = climbOpt.get();
        return climb;
    }

    @Override
    public Page<Climb> getAllClimbs(Pageable pageable) {
        Page<Climb> climbPage = climbRepository.findAll(pageable);
        if (climbPage == null || climbPage.isEmpty()) {
            throw new ResourceNotFoundException("climbs not found");
        }
        return climbPage;
    }
}
