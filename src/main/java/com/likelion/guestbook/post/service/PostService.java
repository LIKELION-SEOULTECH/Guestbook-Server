package com.likelion.guestbook.post.service;

import com.likelion.guestbook.emotion.entity.Emotion;
import com.likelion.guestbook.emotion.service.EmotionService;
import com.likelion.guestbook.exception.custom.BadRequestException;
import com.likelion.guestbook.exception.custom.UnauthorizedException;
import com.likelion.guestbook.post.entity.Post;
import com.likelion.guestbook.post.repository.PostRepository;
import com.likelion.guestbook.post.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final EmotionService emotionService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PostResponse createPost(PostRequest.@Valid create request) {
        Emotion emotion = emotionService.getEmotion(request.content());
        Post post = Post.builder()
                .userName(request.userName())
                .password(request.password())
                .content(request.content())
                .emotion(emotion)
                .build();

        Post savedPost = postRepository.save(post);
        return PostResponse.from(savedPost);
    }

    @Transactional(readOnly = true)
    public PostListResponse getPostList(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> postListPage = postRepository.findAll(pageRequest);

        List<PostResponse> postResponses = postListPage.getContent().stream()
                .map(PostResponse::from)
                .toList();

        return PostListResponse.builder()
                .meta(PostListResponse.MetaData.builder()
                        .totalElements(postListPage.getTotalElements())
                        .page(page)
                        .size(limit)
                        .totalPages(postListPage.getTotalPages())
                        .build())
                .posts(postResponses)
                .build();
    }

    @Transactional
    public PostResponse updatePost(Long id, PostRequest.@Valid update request) {
        Post post = postRepository.findById(id)
                .orElseThrow(BadRequestException::new);

        if (!passwordEncoder.matches(request.password(), post.getPassword())) {
            throw new UnauthorizedException();
        }

        Emotion emotion = emotionService.getEmotion(request.content());
        post.update(request.content(), emotion);

        Post updatedPost = postRepository.save(post);
        return PostResponse.from(updatedPost);
    }

    @Transactional
    public void deletePost(Long id, PostRequest.@Valid delete request) {
        Post post = postRepository.findById(id)
                .orElseThrow(UnauthorizedException::new);

        if (!passwordEncoder.matches(request.password(), post.getPassword())) {
            throw new UnauthorizedException();
        }

        postRepository.deleteById(id);
    }

}
