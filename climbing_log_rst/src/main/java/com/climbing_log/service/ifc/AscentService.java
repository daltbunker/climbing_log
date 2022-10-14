package com.climbing_log.service.ifc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.climbing_log.model.Ascent;

public interface AscentService {
    Ascent addAscent(Ascent newAscent);

    Ascent getAscentById(Integer id);
    
    Page<Ascent> getAllAscents(Pageable pageable);
}
