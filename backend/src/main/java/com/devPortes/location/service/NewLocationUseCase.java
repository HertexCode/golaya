package com.devPortes.location.service;

import com.devPortes.client.ICloudinaryClient;
import com.devPortes.client.IQrCodeApiClient;
import com.devPortes.location.dto.NewLocationRequestDto;
import com.devPortes.location.dto.NewLocationResponseDto;
import com.devPortes.location.exceptions.ExistingLocationDataBaseException;
import com.devPortes.location.mapper.LocationInMapper;
import com.devPortes.location.model.Location;
import com.devPortes.location.repository.LocationRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewLocationUseCase implements INewLocationUseCase{
    private final LocationRepositoryAdapter locationRepositoryAdapter;
    private final IQrCodeApiClient iQrCodeApiClient;
    private final ICloudinaryClient iCloudinaryClient;

    @Override
    public NewLocationResponseDto execute (NewLocationRequestDto dto){
        byte[] urlQr = iQrCodeApiClient.createQr(dto.urlQrAddress());
        String urlQrCloudinary = iCloudinaryClient.saveQrImg("ubicaciones",urlQr);
        Location location = LocationInMapper.toModel(urlQrCloudinary,dto);

        locationRepositoryAdapter.findByName(location.getName())
                .ifPresent(dbLocation ->{
                    throw new ExistingLocationDataBaseException(dbLocation.getName());});



        Location saveLocation = locationRepositoryAdapter.save(location);
    return LocationInMapper.toNewLocationDto(saveLocation);
    }

}
