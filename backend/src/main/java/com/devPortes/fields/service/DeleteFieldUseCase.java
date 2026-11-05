package com.devPortes.fields.service;

import com.devPortes.fields.exceptions.FieldNotFoundException;
import com.devPortes.fields.repository.FieldJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteFieldUseCase {
    private final FieldJpaRepositoryAdapter repository;

    public void execute(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new FieldNotFoundException(id);
        }
        repository.hide(id);
    }
}
