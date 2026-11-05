package com.devPortes.gallery.mapper;

import com.devPortes.gallery.entities.PostEntity;
import com.devPortes.gallery.model.Post;

import java.util.List;

public class PostOutMapper {

    public static PostEntity toEntity(Post model) {
        PostEntity entity = new PostEntity();

        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setUrlPictures(model.getUrlPictures());
        entity.setEventDate(model.getEventDate());

        return entity;
    }

    public static PostEntity toEditEntity(PostEntity saveEntity, Post model) {
        saveEntity.setName(model.getName());
        saveEntity.setDescription(model.getDescription());
        saveEntity.setUrlPictures(model.getUrlPictures());
        saveEntity.setEventDate(model.getEventDate());

        return saveEntity;
    }

    public static Post toModel(PostEntity entity) {
        return Post.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getUrlPictures(),
                entity.getEventDate()
        );
    }

    public static List<Post> toModelList(List<PostEntity> postsEntity) {
        return postsEntity.stream()
                .map(PostOutMapper::toModel)
                .toList();
    }
}
