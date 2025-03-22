package com.likelion.guestbook.post.dto;

import java.util.List;

public record PostListResponse(
        MetaData meta,
        List<PostResponse> posts
) {
    public record MetaData(
            long page,
            int size,
            int totalPages,
            long totalElements
    ) {
    }
}
