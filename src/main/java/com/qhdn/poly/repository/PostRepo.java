package com.qhdn.poly.repository;

import com.qhdn.poly.model.User;
import com.qhdn.poly.model.Category;
import com.qhdn.poly.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User account);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
