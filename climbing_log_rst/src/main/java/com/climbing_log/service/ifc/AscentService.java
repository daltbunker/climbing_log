package com.climbing_log.service.ifc;

import java.util.List;

import com.climbing_log.model.Ascent;

public interface AscentService {
    Ascent addAscent(Ascent newAscent);

    Ascent updateAscent(Ascent newAscent);

    Ascent getAscentById(Integer id);
    
    List<Ascent> getAllAscents();

    List<Ascent> getAscentsByUserId(Integer userId);
}
