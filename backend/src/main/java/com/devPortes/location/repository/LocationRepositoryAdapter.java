package com.devPortes.location.repository;

import com.devPortes.location.entities.LocationEntity;
import com.devPortes.location.mapper.LocationOutMapper;
import com.devPortes.location.model.Location;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LocationRepositoryAdapter {

    private final ILocationJpaRepository jpa;

    public Optional<Location> findByName(String name){
        Optional<LocationEntity> entity = jpa.findByNameIgnoreCase(name);
        return entity.map(LocationOutMapper::toModel);
    }

    @Transactional
    public Location save (Location locationModel){
        LocationEntity entity = LocationOutMapper.toEntity(locationModel);
        return LocationOutMapper.toModel(jpa.save(entity));
    }

    @Transactional
    public Location editLocation(Location locationModel, Long id) {
        LocationEntity savedEntity = jpa.findById(id)
                .orElseThrow(() -> new com.devPortes.location.exceptions.LocationRepositoryNotFoundException(id));
        LocationEntity editedEntity = LocationOutMapper.toEditEntity(savedEntity, locationModel);
        return LocationOutMapper.toModel(jpa.save(editedEntity));
    }

    @Transactional
    public Location changeState(Long id, boolean currentState) {
        LocationEntity savedEntity = jpa.findById(id)
                .orElseThrow(() -> new com.devPortes.location.exceptions.LocationRepositoryNotFoundException(id));
        Location locationModel = LocationOutMapper.toModel(savedEntity);
        Location toggledLocation = Location.changeState(
                locationModel.getId(),
                locationModel.getName(),
                locationModel.getHeadquarters(),
                locationModel.getAddress(),
                locationModel.getUrlQrAddress(),
                locationModel.getUrlAddress(),
                locationModel.getDescription(),
                locationModel.isState()
        );
        LocationEntity toggledEntity = LocationOutMapper.toEditEntity(savedEntity, toggledLocation);
        return LocationOutMapper.toModel(jpa.save(toggledEntity));
    }
    public Optional<Location> findById(Long id){
        Optional<LocationEntity> entity = jpa.findById(id);
        return entity.map(LocationOutMapper::toModel);
    }

    public List<Location> findAll(){
        return LocationOutMapper.toModelList(jpa.findAllByVisibleTrue());
    }

    @Transactional
    public void hide(Long id) {
        LocationEntity entity = jpa.findById(id)
                .orElseThrow(() -> new com.devPortes.location.exceptions.LocationRepositoryNotFoundException(id));
        entity.setVisible(false);
        jpa.save(entity);
    }
}
