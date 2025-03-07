package com.likelion.guestbook.common.dto;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
        boolean success,
        T data,
        ErrorResponse error
) {
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static ApiResponse<Object> error(String code, String message) {
        return ApiResponse.builder()
                .success(false)
                .data(null)
                .error(new ErrorResponse(code, message))
                .build();
    }

    public record ErrorResponse(
            String code,
            String message
    ) {
    }
}
