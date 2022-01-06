package com.SelimGezer.myDemo.Repository;

import com.SelimGezer.myDemo.Entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeDao extends JpaRepository<Like,Long> {
    List<Like> findLikeByPost_Id(Long id);

}
