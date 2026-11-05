package com.devPortes.fields.service;

import com.devPortes.client.ICloudinaryClient;
import com.devPortes.fields.dto.EditFieldRequestDto;
import com.devPortes.fields.dto.FieldsCompleteResponseDto;
import com.devPortes.fields.exceptions.FieldNotFoundException;
import com.devPortes.fields.exceptions.FieldRepositoryNotFoundException;
import com.devPortes.fields.mapper.FieldInMapper;
import com.devPortes.fields.model.Field;
import com.devPortes.fields.repository.FieldJpaRepositoryAdapter;
import com.devPortes.location.exceptions.LocationNotFoundException;
import com.devPortes.location.model.Location;
import com.devPortes.location.service.IFindLocationByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditFieldUseCase implements IEditFieldUseCase{
    private final FieldJpaRepositoryAdapter fieldRepository;
    private final IFindLocationByIdUseCase iFindLocationById;
    private final ICloudinaryClient iCloudinaryClient;


    @Override
    public FieldsCompleteResponseDto execute(Long id, EditFieldRequestDto dto) {
        String urlImg;
        //Busco la cancha por el id
        Field saveField = fieldRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException(id));
        //reviso que la location tambien exista
        Long locationId = dto.locationId();
        Location location = iFindLocationById.execute(locationId)
                .orElseThrow(()-> new LocationNotFoundException(locationId));

        //debo revisar si vienen nuevas imagenes
        if (dto.pictures()!= null){
            List<String> urlPictures = dto.UrlPictures() != null ? new ArrayList<>(dto.UrlPictures()) : new ArrayList<>();
            for(int i=0; i < dto.pictures().size() ;i++){
                urlImg = iCloudinaryClient.saveImg("canchas",dto.pictures().get(i));
                urlPictures.add(urlImg);
            }
            dto = new EditFieldRequestDto(
                locationId, urlPictures, dto.pictures(), dto.name(),
                dto.capacity(), dto.sport(), dto.surface(), dto.description(),
                dto.details(), dto.hourlyRate(), dto.state()
            );
        }

        //reemplazo los datos
        Field editField = FieldInMapper.toEditModel(dto, location);

        //Guardo los nuevos datos y retorno la cancha guardada
        return FieldInMapper.toFieldsCompleteResponseDto(fieldRepository.editField(editField, id));
    }
}
