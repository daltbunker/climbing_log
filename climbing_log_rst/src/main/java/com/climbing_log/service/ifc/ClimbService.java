package com.climbing_log.service.ifc;

import java.util.List;

import com.climbing_log.model.Climb;

public interface ClimbService {
    Climb addClimb(Climb newClimb);

    Climb getClimbById(Integer id);

    List<Climb> getAllClimbs();
    
    List<Climb> getClimbsByLocation(Integer id);

    List<Climb> getClimbsByName(String name);
}
