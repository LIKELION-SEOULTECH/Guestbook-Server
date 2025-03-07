package com.likelion.guestbook.post.repository;

import com.likelion.guestbook.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
