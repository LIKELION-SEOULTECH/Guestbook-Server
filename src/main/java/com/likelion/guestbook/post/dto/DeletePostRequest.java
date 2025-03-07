package com.likelion.guestbook.post.dto;

import jakarta.validation.constraints.NotBlank;

public record DeletePostRequest(
        @NotBlank(message = "비밀번호는 필수입니다")
        String password
) {
}
