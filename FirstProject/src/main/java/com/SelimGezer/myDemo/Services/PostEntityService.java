package com.SelimGezer.myDemo.Services;

import com.SelimGezer.myDemo.Dtos.PostRequestDto;
import com.SelimGezer.myDemo.Dtos.PostResponceDto;
import com.SelimGezer.myDemo.Entities.Post;
import com.SelimGezer.myDemo.Entities.User;
import com.SelimGezer.myDemo.Exception.PostNotFound;
import com.SelimGezer.myDemo.Exception.UserNotFound;
import com.SelimGezer.myDemo.Repository.PostDao;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostEntityService {

    private final PostDao postDao;
    private final UserEntityService userEntityService;

    public PostEntityService(PostDao postDao, UserEntityService userEntityService) {
        this.postDao = postDao;
        this.userEntityService = userEntityService;
    }


    public PostResponceDto getPostById(Long id) {
        Optional<Post> optionalPost = postDao.findById(id);
        PostResponceDto postResponceDto = new PostResponceDto();
        User user;

        user = (User) Hibernate.unproxy(optionalPost.get().getUser());
        //user=optionalPost.get().getUser();
        System.out.println(user.getEmail());
        postResponceDto.setUser(user);
        postResponceDto.setId(id);
        postResponceDto.setComment(optionalPost.get().getComment());
        if (optionalPost.isPresent()) {
            return postResponceDto;// PostMapper.toDto( optionalPost.get());
        } else {
            throw new PostNotFound(String.format("Belirtilen %d ye sahip bir post bulunamadı!", id));
        }
    }


    public Post findPost(Long id) {
        Optional<Post> optionalPost = postDao.findById(id);
        if (optionalPost.isPresent()) {
            return optionalPost.get();
        } else {
            return null;
        }
    }

    public String addPost(PostRequestDto postRequestDto) {
        User user = userEntityService.findUser(postRequestDto.getUserId());
        if (user != null) {
            Post post = new Post();
            post.setUser(user);
            postDao.save(post);
            return new String("Post başarı ile eklendi");
        } else {
            throw new UserNotFound(String.format("Belirtilen %d ye sahip bir kullanıcı bulunamadı!", postRequestDto.getUserId()));
        }
    }

    public String deletePostById(Long id) {
        Optional<Post> optionalPost = postDao.findById(id);
        if (optionalPost.isPresent()) {
            postDao.deleteById(id);
            return new String("Post başarı ile silindi");
        } else {
            throw new PostNotFound(String.format("Belirtilen %d ye sahip bir post bulunamadı!", id));
        }
    }
}
