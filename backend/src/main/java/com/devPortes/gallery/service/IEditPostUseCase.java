package com.devPortes.gallery.service;

import com.devPortes.gallery.dto.EditPostRequestDto;
import com.devPortes.gallery.dto.PostsCompleteResponseDto;

public interface IEditPostUseCase {
    PostsCompleteResponseDto execute(
            Long id,
            EditPostRequestDto dto
    );
}
