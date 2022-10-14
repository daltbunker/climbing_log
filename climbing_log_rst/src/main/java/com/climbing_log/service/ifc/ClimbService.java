package com.climbing_log.service.ifc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.climbing_log.model.Climb;

public interface ClimbService {
    Climb addClimb(Climb newClimb);

    Climb getClimbById(Integer id);

    Page<Climb> getAllClimbs(Pageable pageable);
}
