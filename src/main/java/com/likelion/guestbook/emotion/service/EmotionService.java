package com.likelion.guestbook.emotion.service;

import com.likelion.guestbook.emotion.dto.EmotionAnalysis;
import com.likelion.guestbook.emotion.entity.Emotion;
import com.likelion.guestbook.exception.custom.EmotionApiErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmotionService {
    private final WebClient webClient;

    @Value("${EMOTION_ANALYSIS_URL}")
    private String emotionAnalysisUrl;

    private static final Map<String, Emotion> EMOTION_MAPPING = Map.of(
            "공포", Emotion.HORROR,
            "놀람", Emotion.SURPRISE,
            "분노", Emotion.ANGER,
            "슬픔", Emotion.SORROW,
            "중립", Emotion.NEUTRALITY,
            "행복", Emotion.HAPPINESS,
            "혐오", Emotion.AVERSION
    );

    public Emotion getEmotion(String content) {
        try {
            log.info("감정 분석 API 호출: {}", emotionAnalysisUrl);

            EmotionAnalysis.request request = new EmotionAnalysis.request(content);

            EmotionAnalysis.response response = webClient.post()
                    .uri(emotionAnalysisUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(EmotionAnalysis.response.class)
                    .onErrorResume(e -> {
                        log.error("감정 분석 API 호출 중 오류 발생: {}", e.getMessage());
                        return Mono.error(new EmotionApiErrorException());
                    })
                    .block();

            if (response != null && response.emotion() != null) {
                log.info("감정 분석 결과: {}", response.emotion());

                Emotion mappedEmotion = EMOTION_MAPPING.getOrDefault(response.emotion(), null);

                if (mappedEmotion != null) {
                    return mappedEmotion;
                } else {
                    log.error("알 수 없는 감정 유형: {}", response.emotion());
                    throw new EmotionApiErrorException();
                }
            } else {
                log.warn("감정 분석 API 응답이 비어있습니다.");
                throw new EmotionApiErrorException();
            }
        } catch (Exception e) {
            if (e instanceof EmotionApiErrorException) {
                throw e;
            }
            log.error("예상치 못한 오류 발생: {}", e.getMessage());
            throw new EmotionApiErrorException();
        }
    }
}