package com.climbing_log.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.climbing_log.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    public Page<Location> findAll(Pageable pageable);
}
