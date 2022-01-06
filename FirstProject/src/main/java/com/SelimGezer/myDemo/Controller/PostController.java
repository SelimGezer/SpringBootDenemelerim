package com.SelimGezer.myDemo.Controller;

import com.SelimGezer.myDemo.Dtos.PostRequestDto;
import com.SelimGezer.myDemo.Dtos.PostResponceDto;
import com.SelimGezer.myDemo.Entities.Post;
import com.SelimGezer.myDemo.Services.PostEntityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostEntityService postEntityService;

    public PostController(PostEntityService postEntityService) {
        this.postEntityService = postEntityService;
    }

    @GetMapping("/{id}")
    public PostResponceDto getPostById(@PathVariable(name = "id") Long id){
        return postEntityService.getPostById(id);
    }

    @PostMapping()
    public String addPost(@RequestBody PostRequestDto postRequestDto){
        return postEntityService.addPost(postRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deletePostById(@PathVariable("id") Long id){
        return postEntityService.deletePostById(id);
    }
}
