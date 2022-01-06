package com.SelimGezer.myDemo.Repository;

import com.SelimGezer.myDemo.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post,Long> {
}
