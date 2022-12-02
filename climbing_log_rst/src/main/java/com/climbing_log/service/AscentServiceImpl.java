package com.climbing_log.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Ascent updateAscent(Ascent updatedAscent) {
        Ascent prevAscent = this.getAscentById(updatedAscent.getId());
        prevAscent.setAttempts(updatedAscent.getAttempts());
        prevAscent.setComment(updatedAscent.getComment());
        prevAscent.setDate(updatedAscent.getDate());

        Ascent ascent = ascentRepository.save(prevAscent);
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
    public List<Ascent> getAllAscents() {
        List<Ascent> ascentPage = ascentRepository.findAll();
        if (ascentPage == null || ascentPage.isEmpty()) {
            throw new ResourceNotFoundException("ascents not found");
        }
        return ascentPage ;
    }

    @Override
    public List<Ascent> getAscentsByUserId(Integer userId) {
        List<Ascent> ascents = ascentRepository.findAllByUserId(userId);
        if (ascents == null || ascents.isEmpty()) {
            throw new ResourceNotFoundException("ascents not found");
        }
        return ascents;
    }

}
