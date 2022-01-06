package com.SelimGezer.myDemo.Services;

import com.SelimGezer.myDemo.Dtos.CommentAddDto;
import com.SelimGezer.myDemo.Dtos.CommentRequestDto;
import com.SelimGezer.myDemo.Dtos.CommentResponceDto;
import com.SelimGezer.myDemo.Entities.Comment;
import com.SelimGezer.myDemo.Entities.Post;
import com.SelimGezer.myDemo.Entities.User;
import com.SelimGezer.myDemo.Exception.CommentNotFound;
import com.SelimGezer.myDemo.Exception.PostNotFound;
import com.SelimGezer.myDemo.Exception.UserNotFound;
import com.SelimGezer.myDemo.Mapper.CommentMapper;
import com.SelimGezer.myDemo.Repository.CommentDao;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentEntityService {

    private final CommentDao commentDao;
    private final UserEntityService userEntityService;
    private final PostEntityService postEntityService;

    public CommentEntityService(CommentDao commentDao, UserEntityService userEntityService, PostEntityService postEntityService) {
        this.commentDao = commentDao;
        this.userEntityService = userEntityService;
        this.postEntityService = postEntityService;
    }


    public List<Comment> commentByUserId(Long userId) {
       List<Comment> commentList = commentDao.findCommentByUser_Id(userId);
        if(commentList.size()>0){
            return commentList;
        }else{
            throw new UserNotFound(String.format("Belirtilen %d ye sahip kullanıcının hiç yorumu bulunamadı!",userId));
        }
    }

    public String deleteCommentById(Long id) {
        Optional<Comment> optionalComment = commentDao.findById(id);
        if(optionalComment.isPresent()){
            commentDao.deleteById(id);
            return new String("Yorum başarıyla silindi!");
        }else{
            throw new CommentNotFound(String.format("Belirtilen %d ye sahip bir yorum bulunamadı!",id));
        }
    }

    public CommentResponceDto updateCommentById(Long id, CommentRequestDto commentRequestDto) {
        Optional<Comment> optionalComment = commentDao.findById(id);
        if(optionalComment.isPresent()){
            Comment comment =optionalComment.get();
            comment.setComment(commentRequestDto.getComment());
            Comment save = commentDao.save(comment);
            return CommentMapper.toDto(save);
        }else{
            throw new CommentNotFound(String.format("Belirtilen %d ye sahip bir yorum bulunamadı!",id));
        }
    }
    public CommentResponceDto addComment(CommentAddDto commentAddDto) {
        User user = userEntityService.findUser(commentAddDto.getUserId());
        Post post= postEntityService.findPost(commentAddDto.getPostId());

        if(user==null){
            throw new UserNotFound("Kullanıcı bulunamadı!");
        }
        if(post==null){
            throw new PostNotFound("Post Bulunamadı!");
        }

        Comment comment=new Comment();
        comment.setCommentDate(commentAddDto.getCommentDate());
        comment.setComment(commentAddDto.getComment());
        comment.setUser(user);
        comment.setPostMap(post);

        Comment save = commentDao.save(comment);
        return CommentMapper.toDto(save);
    }
}
