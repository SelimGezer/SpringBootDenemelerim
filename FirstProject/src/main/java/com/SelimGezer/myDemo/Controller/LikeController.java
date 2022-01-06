package com.SelimGezer.myDemo.Controller;

import com.SelimGezer.myDemo.Dtos.LikeRequestDto;
import com.SelimGezer.myDemo.Dtos.LikeResponceDto;
import com.SelimGezer.myDemo.Services.LikeEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/likes")
public class LikeController {

    private final LikeEntityService likeEntityService;


    public LikeController(LikeEntityService likeEntityService) {
        this.likeEntityService = likeEntityService;
    }


    @GetMapping("/{postId}")
    public List<LikeResponceDto> getLikeByPostId(@PathVariable("postId") Long id) {
        return likeEntityService.getLikeByPostId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteLikeById(@PathVariable("id") Long id){
        return likeEntityService.deleteLikeById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String addLike(@RequestBody LikeRequestDto likeRequestDto){
        return likeEntityService.addLike(likeRequestDto);
    }



}
