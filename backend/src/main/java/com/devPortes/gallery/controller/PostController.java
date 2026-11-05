package com.devPortes.gallery.controller;

import com.devPortes.gallery.dto.EditPostRequestDto;
import com.devPortes.gallery.dto.NewPostRequestDto;
import com.devPortes.gallery.dto.NewPostResponseDto;
import com.devPortes.gallery.dto.PostsCompleteResponseDto;
import com.devPortes.gallery.service.IAllPostsUseCase;
import com.devPortes.gallery.service.IDeletePostUseCase;
import com.devPortes.gallery.service.IEditPostUseCase;
import com.devPortes.gallery.service.INewPostUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/post")
public class PostController {

    private final INewPostUseCase iNewPostUseCase;
    private final IEditPostUseCase iEditPostUseCase;
    private final IAllPostsUseCase iAllPostsUseCase;
    private final IDeletePostUseCase iDeletePostUseCase;


    @PostMapping(
            value = "new",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<NewPostResponseDto> newPost(
            @Valid @ModelAttribute NewPostRequestDto dto) {

        NewPostResponseDto response =
                iNewPostUseCase.execute(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PutMapping(
            value = "edit/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<PostsCompleteResponseDto> editPost(
            @PathVariable Long id,
            @Valid @ModelAttribute EditPostRequestDto dto) {

        PostsCompleteResponseDto response =
                iEditPostUseCase.execute(id, dto);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(response);
    }


    @GetMapping("all")
    public ResponseEntity<List<PostsCompleteResponseDto>> allPosts() {

        List<PostsCompleteResponseDto> posts =
                iAllPostsUseCase.execute();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(posts);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id) {

        iDeletePostUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }
}