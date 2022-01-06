package com.SelimGezer.myDemo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Like not found")
public class LikeNotFound extends RuntimeException {

    public LikeNotFound(String message) {
        super(message);
    }
}

