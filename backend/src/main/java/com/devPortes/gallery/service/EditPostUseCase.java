package com.devPortes.gallery.service;

import com.devPortes.client.ICloudinaryClient;
import com.devPortes.gallery.dto.EditPostRequestDto;
import com.devPortes.gallery.dto.PostsCompleteResponseDto;
import com.devPortes.gallery.mapper.PostInMapper;
import com.devPortes.gallery.model.Post;
import com.devPortes.gallery.repository.PostJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditPostUseCase implements IEditPostUseCase {

    private final PostJpaRepositoryAdapter postJpaRepository;
    private final ICloudinaryClient iCloudinaryClient;

    @Override
    public PostsCompleteResponseDto execute(Long id, EditPostRequestDto dto) {

        List<String> urlPictures = new ArrayList<>();

        if (dto.urlPictures() != null) {
            urlPictures.addAll(dto.urlPictures());
        }

        if (dto.pictures() != null) {
            for (MultipartFile picture : dto.pictures()) {
                String urlImg = iCloudinaryClient.saveImg("galería", picture);
                urlPictures.add(urlImg);
            }
        }

        Post post = PostInMapper.toEditModel(
                dto,
                urlPictures
        );

        Post editedPost = postJpaRepository.editPost(post, id);

        return PostInMapper.toPostsCompleteResponseDto(editedPost);
    }
}
