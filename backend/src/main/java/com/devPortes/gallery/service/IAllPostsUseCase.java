package com.devPortes.gallery.service;

import com.devPortes.gallery.dto.PostsCompleteResponseDto;

import java.util.List;

public interface IAllPostsUseCase {
    List<PostsCompleteResponseDto> execute();
}
