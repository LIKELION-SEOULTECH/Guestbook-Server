package com.likelion.guestbook.exception.custom;

import com.likelion.guestbook.exception.code.ErrorCode;

public class InternalServerErrorException extends BusinessException {
    public InternalServerErrorException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
