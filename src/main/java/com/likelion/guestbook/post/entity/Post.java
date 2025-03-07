package com.likelion.guestbook.post.entity;

import com.likelion.guestbook.common.entity.BaseEntity;
import com.likelion.guestbook.emotion.entity.Emotion;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "content")
    private String content;

    @Column(name = "emotion")
    private Emotion emotion;

    @Builder
    public Post(
            String userName,
            String password,
            String content,
            Emotion emotion
    ) {
        this.userName = userName;
        this.password = password;
        this.content = content;
        this.emotion = emotion;
    }

    public void update(String content, Emotion emotion) {
        this.content = content;
        this.emotion = emotion;
    }

}
