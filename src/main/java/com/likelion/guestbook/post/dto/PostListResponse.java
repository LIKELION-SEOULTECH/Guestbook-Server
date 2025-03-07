package com.likelion.guestbook.post.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PostListResponse(
        MetaData meta,
        List<PostResponse> posts

) {
    @Builder
    public static class MetaData {
        private long page;
        private int size;
        private int totalPages;
        private long totalElements;
    }
}
