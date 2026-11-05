package com.devPortes.location.controller;

import com.devPortes.location.dto.ListLocationsResponseDto;
import com.devPortes.location.dto.NewLocationRequestDto;
import com.devPortes.location.dto.LocationCompleteResponseDto;
import com.devPortes.location.dto.NewLocationResponseDto;
import com.devPortes.location.dto.UpdateLocationRequestDto;
import com.devPortes.location.service.IChangeLocationStateUseCase;
import com.devPortes.location.service.IListLocationsUseCase;
import com.devPortes.location.service.INewLocationUseCase;
import com.devPortes.location.service.IUpdateLocationUseCase;
import com.devPortes.location.service.DeleteLocationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/location")
@RequiredArgsConstructor
public class LocationController {
    private final INewLocationUseCase iNewLocationUseCase;
    private final IListLocationsUseCase iListLocationsUseCase;
    private final IUpdateLocationUseCase iUpdateLocationUseCase;
    private final IChangeLocationStateUseCase iChangeLocationStateUseCase;
    private final DeleteLocationUseCase deleteLocationUseCase;

    @PostMapping("new-location")
    public ResponseEntity<NewLocationResponseDto> newLocation (@Valid @RequestBody NewLocationRequestDto dto){
        NewLocationResponseDto response = iNewLocationUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("locations")
    public ResponseEntity<ListLocationsResponseDto> listLocation(){
        ListLocationsResponseDto location = iListLocationsUseCase.execute();
        return ResponseEntity.ok().body(location);
    }

    @PutMapping("{id}")
    public ResponseEntity<LocationCompleteResponseDto> updateLocation(
            @PathVariable Long id,
            @Valid @RequestBody UpdateLocationRequestDto dto){
        LocationCompleteResponseDto response = iUpdateLocationUseCase.execute(id, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PatchMapping("{id}/state")
    public ResponseEntity<LocationCompleteResponseDto> changeState(@PathVariable Long id){
        LocationCompleteResponseDto response = iChangeLocationStateUseCase.execute(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        deleteLocationUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
