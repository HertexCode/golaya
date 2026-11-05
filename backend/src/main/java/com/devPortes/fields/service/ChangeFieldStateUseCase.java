package com.devPortes.fields.service;

import com.devPortes.fields.dto.FieldsCompleteResponseDto;
import com.devPortes.fields.exceptions.FieldNotFoundException;
import com.devPortes.fields.mapper.FieldInMapper;
import com.devPortes.fields.model.Field;
import com.devPortes.fields.model.FieldStateEnum;
import com.devPortes.fields.repository.FieldJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeFieldStateUseCase implements IChangeFieldStateUseCase {
    private final FieldJpaRepositoryAdapter fieldRepository;

    @Override
    public FieldsCompleteResponseDto execute(Long id) {
        Field existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException(id));

        FieldStateEnum nextState = existingField.getState() == FieldStateEnum.DISPONIBLE
                ? FieldStateEnum.MANTENIMIENTO
                : FieldStateEnum.DISPONIBLE;

        return FieldInMapper.toFieldsCompleteResponseDto(fieldRepository.changeState(id, nextState));
    }
}
