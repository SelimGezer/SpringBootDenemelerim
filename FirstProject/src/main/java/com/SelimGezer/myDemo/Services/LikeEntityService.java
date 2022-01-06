package com.SelimGezer.myDemo.Services;

import com.SelimGezer.myDemo.Dtos.LikeRequestDto;
import com.SelimGezer.myDemo.Dtos.LikeResponceDto;
import com.SelimGezer.myDemo.Entities.Like;
import com.SelimGezer.myDemo.Entities.Post;
import com.SelimGezer.myDemo.Entities.User;
import com.SelimGezer.myDemo.Exception.LikeNotFound;
import com.SelimGezer.myDemo.Exception.PostNotFound;
import com.SelimGezer.myDemo.Exception.UserNotFound;
import com.SelimGezer.myDemo.Repository.LikeDao;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeEntityService {

    private final LikeDao likeDao;
    private PostEntityService postEntityService;
    private UserEntityService userEntityService;

    public LikeEntityService(LikeDao likeDao, PostEntityService postEntityService, UserEntityService userEntityService) {
        this.likeDao = likeDao;
        this.postEntityService = postEntityService;
        this.userEntityService = userEntityService;
    }

    public List<LikeResponceDto> getLikeByPostId(Long id) {
        List<Like> likeList = likeDao.findLikeByPost_Id(id);
        if(likeList.size()>0){
            return likeList.stream().map(like -> new LikeResponceDto(like.getUser())).collect(Collectors.toList());
        }else{
            throw new PostNotFound(String.format("Belirtilen %d ye sahip bir post bulunamadı!", id));
        }
    }

    public String deleteLikeById(Long id) {
        Optional<Like> optionalLike = likeDao.findById(id);
        if(optionalLike.isPresent()){
            likeDao.deleteById(id);
            return new String("Like başarı ile silindi!");
        }else{
            throw new LikeNotFound(String.format("Belirtilen %d ye sahip bir like bulunamadı!", id));
        }
    }

    public String addLike(LikeRequestDto likeRequestDto) {
        Post post = postEntityService.findPost(likeRequestDto.getPostId());
        User user = userEntityService.findUser(likeRequestDto.getUserId());

        if(post==null){
            throw new PostNotFound(String.format("Belirtilen %d ye sahip bir post bulunamadı!",likeRequestDto.getPostId()));
        }
        if(user==null){
            throw new UserNotFound(String.format("Belirtilen %d ye sahip bir kullanıcı bulunamadı!",likeRequestDto.getUserId()));
        }

        Like like=new Like();
        like.setUser(user);
        like.setPost(post);
        likeDao.save(like);
        return new String("Like başarı ile eklendi!");
    }
}
