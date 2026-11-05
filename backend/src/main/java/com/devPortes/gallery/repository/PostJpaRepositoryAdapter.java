package com.devPortes.gallery.repository;


import com.devPortes.gallery.entities.PostEntity;
import com.devPortes.gallery.exceptions.PostNotFoundException;
import com.devPortes.gallery.mapper.PostOutMapper;
import com.devPortes.gallery.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostJpaRepositoryAdapter {

    private final IPostJpaRepository repository;

    public Post save(Post model) {
        PostEntity entity = PostOutMapper.toEntity(model);

        PostEntity savedEntity = repository.save(entity);

        return PostOutMapper.toModel(savedEntity);
    }

    public Optional<Post> findById(Long id) {
        return repository.findById(id)
                .map(PostOutMapper::toModel);
    }

    public List<Post> findAll() {
        return PostOutMapper.toModelList(repository.findAllByOrderByEventDateDesc());
    }

    public Post editPost(Post model, Long id) {
        PostEntity saveEntity = repository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        PostEntity editedEntity =
                PostOutMapper.toEditEntity(saveEntity, model);

        PostEntity savedEntity = repository.save(editedEntity);

        return PostOutMapper.toModel(savedEntity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
