package com.likelion.guestbook.post.controller;

import com.likelion.guestbook.common.dto.ApiResponse;
import com.likelion.guestbook.post.service.PostService;
import com.likelion.guestbook.post.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostResponse> createPost(@Valid @RequestBody PostRequest.create request) {
        return ApiResponse.success(postService.createPost(request));
    }

    @GetMapping
    public ApiResponse<PostListResponse> getPostList(
            @Valid @RequestParam(defaultValue = "1") @Min(1) int page,
            @Valid @RequestParam(defaultValue = "10") @Min(1) @Max(50) int limit) {
        return ApiResponse.success(postService.getPostList(page, limit));
    }

    @PatchMapping("/{id}")
    public ApiResponse<PostResponse> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest.update request) {
        return ApiResponse.success(postService.updatePost(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest.delete request) {
        postService.deletePost(id, request);
    }
}
