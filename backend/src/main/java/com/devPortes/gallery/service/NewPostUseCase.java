package com.devPortes.gallery.service;

import com.devPortes.client.ICloudinaryClient;
import com.devPortes.gallery.dto.NewPostRequestDto;
import com.devPortes.gallery.dto.NewPostResponseDto;
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
public class NewPostUseCase implements INewPostUseCase {

    private final ICloudinaryClient iCloudinaryClient;
    private final PostJpaRepositoryAdapter postJpaRepository;

    @Override
    public NewPostResponseDto execute(NewPostRequestDto dto) {

        List<String> urlPictures = new ArrayList<>();

        for (MultipartFile picture : dto.pictures()) {
            String urlImg = iCloudinaryClient.saveImg("galería",picture);
            urlPictures.add(urlImg);
        }

        Post post = PostInMapper.toModel(dto, urlPictures);

        Post savedPost = postJpaRepository.save(post);

        return PostInMapper.toNewPostResponseDto(savedPost);
    }
}
