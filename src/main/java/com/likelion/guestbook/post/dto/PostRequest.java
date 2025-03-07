package com.likelion.guestbook.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
) {
        public record create(
                @NotBlank(message = "작성자 이름은 필수입니다.")
                @Size(min = 1, max = 10, message = "작성자 이름은 1자 이상 10자 이하여야 합니다")
                String userName,

                @NotBlank(message = "비밀번호는 필수입니다")
                @Size(min = 4, max = 10, message = "비밀번호는 4자 이상 10자 이하여야 합니다")
                String password,

                @NotBlank(message = "내용은 필수입니다")
                @Size(min = 10, max = 100, message = "내용은 10자 이상 100자 이하여야 합니다")
                String content
        ) {
        }

        public record update(
                @NotBlank(message = "비밀번호는 필수입니다")
                @Size(min = 4, max = 10, message = "비밀번호는 4자 이상 10자 이하여야 합니다")
                String password,

                @NotBlank(message = "내용은 필수입니다")
                @Size(min = 10, max = 100, message = "내용은 10자 이상 100자 이하여야 합니다")
                String content
        ) {
        }

        public record delete(
                @NotBlank(message = "비밀번호는 필수입니다")
                String password
        ) {
        }
}
