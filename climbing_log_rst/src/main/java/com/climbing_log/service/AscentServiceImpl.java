package com.climbing_log.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Ascent;
import com.climbing_log.repository.AscentRepository;
import com.climbing_log.service.ifc.AscentService;

@Service
public class AscentServiceImpl implements AscentService {
    @Autowired
    AscentRepository ascentRepository;

    @Override
    public Ascent addAscent(Ascent newAscent) {
        Ascent ascent = ascentRepository.save(newAscent);
        return ascent;
    }

    @Override
    public Ascent getAscentById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ascent id must not be null");
        }
        Optional<Ascent> ascentOpt = ascentRepository.findById(id);

        if (ascentOpt.isEmpty()) {
            throw new ResourceNotFoundException("ascent not found");
        }

        Ascent ascent = ascentOpt.get();
        return ascent;
    }

    @Override
    public Page<Ascent> getAllAscents(Pageable pageable) {
        Page<Ascent> ascentPage = ascentRepository.findAll(pageable);
        if (ascentPage == null || ascentPage.isEmpty()) {
            throw new ResourceNotFoundException("ascents not found");
        }
        return ascentPage ;
    }
    
}
