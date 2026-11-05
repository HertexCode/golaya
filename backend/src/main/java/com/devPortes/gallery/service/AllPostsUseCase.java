package com.devPortes.gallery.service;

import com.devPortes.gallery.dto.PostsCompleteResponseDto;
import com.devPortes.gallery.mapper.PostInMapper;
import com.devPortes.gallery.model.Post;
import com.devPortes.gallery.repository.PostJpaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllPostsUseCase implements IAllPostsUseCase {

    private final PostJpaRepositoryAdapter postJpaRepository;

    @Override
    public List<PostsCompleteResponseDto> execute() {

        List<Post> posts = postJpaRepository.findAll();

        return PostInMapper.toPostsCompleteResponseDtoList(posts);
    }
}