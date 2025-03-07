package com.likelion.guestbook.emotion.service;

import com.likelion.guestbook.emotion.dto.EmotionAnalysis;
import com.likelion.guestbook.exception.custom.EmotionApiErrorException;
import com.likelion.guestbook.emotion.entity.Emotion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmotionService {
    private final RestTemplate restTemplate;

    @Value("${emotion.analysis.url:http://localhost:5000/analyze}")
    private String emotionAnalysisUrl;

    public Emotion getEmotion(String content) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            EmotionAnalysis.request request = new EmotionAnalysis.request(content);
            HttpEntity<EmotionAnalysis.request> entity = new HttpEntity<>(request, headers);

            log.info("감정 분석 API 호출: {}", emotionAnalysisUrl);
            EmotionAnalysis.response response = restTemplate.postForObject(
                    emotionAnalysisUrl,
                    entity,
                    EmotionAnalysis.response.class
            );

            if (response != null && response.emotion() != null) {
                log.info("감정 분석 결과: {}", response.emotion());
                try {
                    return Emotion.valueOf(response.emotion());
                } catch (IllegalArgumentException e) {
                    log.error("알 수 없는 감정 유형: {}", response.emotion());
                    throw new EmotionApiErrorException();
                }
            } else {
                log.warn("감정 분석 API 응답이 비어있습니다.");
                throw new EmotionApiErrorException();
            }
        } catch (RestClientException e) {
            log.error("감정 분석 API 호출 중 오류 발생: {}", e.getMessage());
            throw new EmotionApiErrorException();
        } catch (Exception e) {
            if (e instanceof EmotionApiErrorException) {
                throw e;
            }
            log.error("예상치 못한 오류 발생: {}", e.getMessage());
            throw new EmotionApiErrorException();
        }
    }
}
