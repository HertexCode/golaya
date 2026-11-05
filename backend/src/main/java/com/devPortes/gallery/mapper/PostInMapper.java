package com.devPortes.gallery.mapper;

import com.devPortes.gallery.dto.EditPostRequestDto;
import com.devPortes.gallery.dto.NewPostRequestDto;
import com.devPortes.gallery.dto.NewPostResponseDto;
import com.devPortes.gallery.dto.PostsCompleteResponseDto;
import com.devPortes.gallery.model.Post;

import java.util.List;

public class PostInMapper {
    public static Post toModel(
            NewPostRequestDto dto,
            List<String> urlPictures) {

        return Post.create(
                dto.name(),
                dto.description(),
                urlPictures,
                dto.eventDate()
        );
    }

    public static Post toEditModel(
            EditPostRequestDto dto,
            List<String> urlPictures) {

        return Post.edit(
                dto.name(),
                dto.description(),
                urlPictures,
                dto.eventDate()
        );
    }

    public static NewPostResponseDto toNewPostResponseDto(Post model) {
        return new NewPostResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getUrlPictures(),
                model.getEventDate()
        );
    }

    public static PostsCompleteResponseDto toPostsCompleteResponseDto(Post model) {
        return new PostsCompleteResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getUrlPictures(),
                model.getEventDate()
        );
    }

    public static List<PostsCompleteResponseDto> toPostsCompleteResponseDtoList(
            List<Post> models) {

        return models.stream()
                .map(PostInMapper::toPostsCompleteResponseDto)
                .toList();
    }
}
