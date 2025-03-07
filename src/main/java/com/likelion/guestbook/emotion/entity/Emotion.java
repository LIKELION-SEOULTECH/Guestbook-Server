package com.likelion.guestbook.emotion.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Emotion {
    UNREST("불안"),
    ANGER("분노"),
    WOUND("상처"),
    EMBARRASSMENT("당황"),
    DELIGHT("기쁨"),
    SORROW("슬픔");

    private final String value;
}
