package com.likelion.guestbook.emotion.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Emotion {
    HORROR("공포"),
    SURPRISE("놀람"),
    ANGER("분노"),
    SORROW("슬픔"),
    NEUTRALITY("중립"),
    HAPPINESS("행복"),
    AVERSION("혐오");

    private final String value;
}
