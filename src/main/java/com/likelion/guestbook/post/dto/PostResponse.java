package com.likelion.guestbook.post.dto;

import com.likelion.guestbook.emotion.entity.Emotion;
import com.likelion.guestbook.post.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostResponse(
        Long id,
        String userName,
        String content,
        Emotion emotion,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .userName(post.getUserName())
                .content(post.getContent())
                .emotion(post.getEmotion() != null ? post.getEmotion().getValue() : "알 수 없음")
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
