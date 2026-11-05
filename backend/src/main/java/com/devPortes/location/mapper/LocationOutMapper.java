package com.devPortes.location.mapper;

import com.devPortes.location.entities.LocationEntity;
import com.devPortes.location.model.Location;

import java.util.List;

public class LocationOutMapper {

    public static LocationEntity toEntity(Location model){
        LocationEntity entity = new LocationEntity();

        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setHeadquarters(model.getHeadquarters());
        entity.setAddress(model.getAddress());
        entity.setUrlQrAddress(model.getUrlQrAddress());
        entity.setUrlAddress(model.getUrlAddress());
        entity.setDescription(model.getDescription());
        entity.setState(model.isState());
        entity.setVisible(model.isVisible());
        return entity;

    }
    public static LocationEntity toEditEntity(LocationEntity saveEntity, Location model){
        saveEntity.setName(model.getName());
        saveEntity.setHeadquarters(model.getHeadquarters());
        saveEntity.setAddress(model.getAddress());
        saveEntity.setUrlQrAddress(model.getUrlQrAddress());
        saveEntity.setUrlAddress(model.getUrlAddress());
        saveEntity.setDescription(model.getDescription());
        saveEntity.setState(model.isState());
        return saveEntity;
    }
    public static Location toModel(LocationEntity entity){
        return Location.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getHeadquarters(),
                entity.getAddress(),
                entity.getUrlQrAddress(),
                entity.getUrlAddress(),
                entity.getDescription(),
                entity.isState(),
                entity.isVisible()
        );
    }
    public static List<Location> toModelList(List<LocationEntity> entities){
        return entities.stream().map(LocationOutMapper::toModel)
                .toList();
    }

}
