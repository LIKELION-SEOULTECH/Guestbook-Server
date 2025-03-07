package com.likelion.guestbook.exception.custom;

import com.likelion.guestbook.exception.code.ErrorCode;

public class BadRequestException extends BusinessException {
    public BadRequestException() {
        super(ErrorCode.BAD_REQUEST);
    }
}
