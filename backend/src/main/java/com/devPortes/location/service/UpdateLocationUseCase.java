package com.devPortes.location.service;

import com.devPortes.client.ICloudinaryClient;
import com.devPortes.client.IQrCodeApiClient;
import com.devPortes.location.dto.LocationCompleteResponseDto;
import com.devPortes.location.dto.UpdateLocationRequestDto;
import com.devPortes.location.exceptions.ExistingLocationDataBaseException;
import com.devPortes.location.exceptions.LocationNotFoundException;
import com.devPortes.location.mapper.LocationInMapper;
import com.devPortes.location.model.Location;
import com.devPortes.location.repository.LocationRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateLocationUseCase implements IUpdateLocationUseCase {
    private final LocationRepositoryAdapter locationRepositoryAdapter;
    private final IQrCodeApiClient iQrCodeApiClient;
    private final ICloudinaryClient iCloudinaryClient;

    @Override
    public LocationCompleteResponseDto execute(Long id, UpdateLocationRequestDto dto) {
        Location existingLocation = locationRepositoryAdapter.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        String qrValue = dto.urlQrAddress();
        String currentQrValue = existingLocation.getUrlQrAddress();
        String addressUrl = dto.urlQrAddress();

        if (qrValue == null || qrValue.isBlank()) {
            qrValue = currentQrValue;
            addressUrl = existingLocation.getUrlAddress();
        } else if (qrValue.equals(currentQrValue)
                && (qrValue.contains("cloudinary.com") || qrValue.matches("(?i).+\\.(png|jpg|jpeg|webp)(\\?.*)?$"))) {
            qrValue = currentQrValue;
        } else {
            byte[] qrImage = iQrCodeApiClient.createQr(qrValue);
            qrValue = iCloudinaryClient.saveQrImg("ubicaciones", qrImage);
        }

        UpdateLocationRequestDto dtoWithQrImage = new UpdateLocationRequestDto(
                dto.name(),
                dto.headquarters(),
                dto.address(),
                qrValue,
                dto.description()
        );
        Location editedLocation = LocationInMapper.toEditModel(dtoWithQrImage);
        editedLocation = Location.edit(
                editedLocation.getName(),
                editedLocation.getHeadquarters(),
                editedLocation.getAddress(),
                qrValue,
                addressUrl,
                editedLocation.getDescription()
        );

        if (!existingLocation.getName().equals(editedLocation.getName())) {
            locationRepositoryAdapter.findByName(editedLocation.getName())
                    .ifPresent(dbLocation -> {
                        throw new ExistingLocationDataBaseException(dbLocation.getName());
                    });
        }

        Location savedLocation = locationRepositoryAdapter.editLocation(editedLocation, id);
        return LocationInMapper.toLocationCompleteDto(savedLocation);
    }
}
