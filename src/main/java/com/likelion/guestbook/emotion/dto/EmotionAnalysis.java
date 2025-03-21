package com.likelion.guestbook.emotion.dto;

import java.util.List;

public record EmotionAnalysis() {
    public record request(
            String sentence
    ) {
    }

    public record response(
            String emotion,
            List<Double> probabilities
    ) {
    }
}
