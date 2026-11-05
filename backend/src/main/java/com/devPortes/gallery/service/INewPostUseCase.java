package com.devPortes.gallery.service;

import com.devPortes.gallery.dto.NewPostRequestDto;
import com.devPortes.gallery.dto.NewPostResponseDto;
import com.devPortes.gallery.dto.PostsCompleteResponseDto;

public interface INewPostUseCase {

    NewPostResponseDto execute(NewPostRequestDto dto);
}