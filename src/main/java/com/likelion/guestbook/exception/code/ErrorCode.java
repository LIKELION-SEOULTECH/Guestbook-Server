package com.likelion.guestbook.exception.code;

import lombok.Getter;

@Getter
public enum ErrorCode {

    /* 4xx Error */
    BAD_REQUEST(400, "BAD_REQUEST", "유효하지 않은 입력입니다."),
    UNAUTHORIZED(401, "UNAUTHORIZED", "접근 권한이 없습니다."),

    /* 5xx Error */
    EMOTION_API_ERROR(500, "EMOTION_API_ERROR", "감정 분석 API 처리 중 오류가 발생했습니다."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.");

    final int statusCode;
    final String message;
    final String errorCode;

    ErrorCode(int statusCode, String message, String errorCode) {
        this.statusCode = statusCode;
        this.message = message;
        this.errorCode = errorCode;
    }
}
