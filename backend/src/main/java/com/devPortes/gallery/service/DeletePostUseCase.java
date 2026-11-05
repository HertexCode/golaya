package com.devPortes.gallery.service;

import com.devPortes.gallery.exceptions.PostNotFoundException;
import com.devPortes.gallery.repository.PostJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostUseCase implements IDeletePostUseCase {

    private final PostJpaRepositoryAdapter postJpaRepository;

    @Override
    public void execute(Long id) {

        postJpaRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        postJpaRepository.deleteById(id);
    }
}
