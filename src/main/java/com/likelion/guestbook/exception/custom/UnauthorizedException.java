package com.likelion.guestbook.exception.custom;

import com.likelion.guestbook.exception.code.ErrorCode;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}
