package com.likelion.guestbook.emotion.service;

import com.likelion.guestbook.exception.custom.EmotionApiErrorException;
import com.likelion.guestbook.emotion.entity.Emotion;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmotionService {
    private final RestTemplate restTemplate;
    private static final String EMOTION_API_URL = "http://localhost:5000/analyze";

    public Emotion getEmotion(String content) {

        Map<String, String> requestBody = Map.of("content", content);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                EMOTION_API_URL,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        if (Boolean.TRUE.equals(Objects.requireNonNull(response.getBody()).get("success"))) {
            @SuppressWarnings("unchecked")
            Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");

            String emotion = (String) data.get("emotion");
            return Emotion.valueOf(emotion);
        } else {
            throw new EmotionApiErrorException();
        }
    }
}
