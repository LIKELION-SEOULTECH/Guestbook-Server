package com.likelion.guestbook.emotion.dto;

public record EmotionAnalysis() {
    public record request(
            String content
    ) {
    }

    public record response(
            String emotion,
            double confidence
    ) {
    }
}
