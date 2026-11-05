package com.devPortes.fields.service;

import com.devPortes.client.ICloudinaryClient;
import com.devPortes.fields.dto.FieldsCompleteResponseDto;
import com.devPortes.fields.dto.NewFieldRequestDto;
import com.devPortes.fields.mapper.FieldInMapper;
import com.devPortes.fields.model.Field;
import com.devPortes.fields.repository.FieldJpaRepositoryAdapter;
import com.devPortes.location.exceptions.LocationNotFoundException;
import com.devPortes.location.model.Location;
import com.devPortes.location.service.IFindLocationByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewFieldUseCase implements INewFieldUseCase {
    private final ICloudinaryClient iCloudinaryClient;
    private final IFindLocationByIdUseCase iFindLocationById;
    private final FieldJpaRepositoryAdapter fieldJpaRepository;

    @Override
    public FieldsCompleteResponseDto execute(NewFieldRequestDto dto) {
        List<String> urlPictures = new ArrayList<>();
        String urlImg;

        //verifico que exista la ubicacion con el id
        Location location = iFindLocationById.execute(dto.locationId())
                .orElseThrow(() -> new LocationNotFoundException(dto.locationId()));
/*
        //debo mandar las urls una por una a cloudinary porque no recibe list de imagenes
        List<String> urlPictures = dto.pictures().stream()
                .map(iCloudinaryClient::saveImg)
                .toList();
*/
        for(int i=0; i < dto.pictures().size() ;i++){
            urlImg = iCloudinaryClient.saveImg("canchas",dto.pictures().get(i));
            urlPictures.add(urlImg); }

        Field field = FieldInMapper.toModel(dto, location, urlPictures);

        return FieldInMapper.toFieldsCompleteResponseDto(
                fieldJpaRepository.save(field));
    }
}
