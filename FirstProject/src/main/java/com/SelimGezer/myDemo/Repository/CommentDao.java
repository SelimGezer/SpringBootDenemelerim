package com.SelimGezer.myDemo.Repository;

import com.SelimGezer.myDemo.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment,Long> {
    List<Comment> findCommentByUser_Id(Long userId);
}
