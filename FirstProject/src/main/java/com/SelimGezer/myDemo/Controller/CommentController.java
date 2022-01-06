package com.SelimGezer.myDemo.Controller;

import com.SelimGezer.myDemo.Dtos.CommentAddDto;
import com.SelimGezer.myDemo.Dtos.CommentRequestDto;
import com.SelimGezer.myDemo.Dtos.CommentResponceDto;
import com.SelimGezer.myDemo.Entities.Comment;
import com.SelimGezer.myDemo.Services.CommentEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    private final CommentEntityService commentEntityService;

    public CommentController(CommentEntityService commentEntityService) {
        this.commentEntityService = commentEntityService;
    }

    @GetMapping("/{id}")
    public List<Comment> commentByUserId(@PathVariable("id") Long userId){
        return commentEntityService.commentByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public String deleteCommentById(@PathVariable("id") Long id){
        return commentEntityService.deleteCommentById(id);
    }

    @PutMapping("/{id}")
    public CommentResponceDto updateCommentById(@PathVariable("id") Long id,@RequestBody CommentRequestDto commentRequestDto){
        return commentEntityService.updateCommentById(id,commentRequestDto);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponceDto addComment(@RequestBody CommentAddDto commentAddDto){
        return commentEntityService.addComment(commentAddDto);
    }
}
