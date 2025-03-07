package com.likelion.guestbook.exception.custom;

import com.likelion.guestbook.exception.code.ErrorCode;

public class EmotionApiErrorException extends BusinessException {
    public EmotionApiErrorException() {
        super(ErrorCode.EMOTION_API_ERROR);
    }
}
