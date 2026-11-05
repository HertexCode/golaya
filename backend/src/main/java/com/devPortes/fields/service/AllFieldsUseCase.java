package com.devPortes.fields.service;

import com.devPortes.fields.dto.FieldsCompleteResponseDto;
import com.devPortes.fields.mapper.FieldInMapper;
import com.devPortes.fields.model.Field;
import com.devPortes.fields.repository.FieldJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllFieldsUseCase implements IAllFieldsUseCase{
    private final FieldJpaRepositoryAdapter fieldJpaRepository;

    @Override
    public List<FieldsCompleteResponseDto> execute(){
        List<Field> fields = fieldJpaRepository.findAll();

        return FieldInMapper.toFieldsCompleteResponseDtoList(fields);
    }
}
