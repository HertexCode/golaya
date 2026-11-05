package com.devPortes.fields.controller;

import com.devPortes.fields.dto.EditFieldRequestDto;
import com.devPortes.fields.dto.FieldsCompleteResponseDto;
import com.devPortes.fields.dto.NewFieldRequestDto;
import com.devPortes.fields.service.IAllFieldsUseCase;
import com.devPortes.fields.service.IEditFieldUseCase;
import com.devPortes.fields.service.IChangeFieldStateUseCase;
import com.devPortes.fields.service.DeleteFieldUseCase;
import com.devPortes.fields.service.INewFieldUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/field")
public class FieldController {
    private final IAllFieldsUseCase iAllFieldsUseCase;
    private final INewFieldUseCase iNewFieldUseCase;
    private final IEditFieldUseCase iEditField;
    private final IChangeFieldStateUseCase iChangeFieldStateUseCase;
    private final DeleteFieldUseCase deleteFieldUseCase;

    @PostMapping(value = "new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FieldsCompleteResponseDto> newField(@Valid @ModelAttribute NewFieldRequestDto dto) {
        FieldsCompleteResponseDto response = iNewFieldUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FieldsCompleteResponseDto> editField( @PathVariable Long id,
                                                                @Valid @ModelAttribute EditFieldRequestDto dto) {
        FieldsCompleteResponseDto response = iEditField.execute(id, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("all")
    public ResponseEntity<List<FieldsCompleteResponseDto>> allFields() {
        List<FieldsCompleteResponseDto> fields = iAllFieldsUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK)
                .body(fields);
    }

    @PatchMapping("{id}/state")
    public ResponseEntity<FieldsCompleteResponseDto> changeState(@PathVariable Long id) {
        return ResponseEntity.ok(iChangeFieldStateUseCase.execute(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        deleteFieldUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}