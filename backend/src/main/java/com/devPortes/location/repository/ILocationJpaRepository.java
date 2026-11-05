package com.devPortes.location.repository;

import com.devPortes.location.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ILocationJpaRepository extends JpaRepository<LocationEntity, Long> {
    java.util.List<LocationEntity> findAllByVisibleTrue();
    @Query("SELECT l FROM LocationEntity l ORDER BY l.id ASC")
    List<LocationEntity> findAll();

    @Query("SELECT l FROM LocationEntity l WHERE LOWER(l.name) = LOWER(:name)")
    Optional<LocationEntity> findByNameIgnoreCase(@Param("name") String name);
}
