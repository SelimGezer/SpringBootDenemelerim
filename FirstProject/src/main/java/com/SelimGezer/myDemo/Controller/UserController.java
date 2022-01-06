package com.SelimGezer.myDemo.Controller;

import com.SelimGezer.myDemo.Dtos.UserRequestDto;
import com.SelimGezer.myDemo.Dtos.UserResponceDto;
import com.SelimGezer.myDemo.Entities.User;
import com.SelimGezer.myDemo.Services.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserEntityService userEntityService;

    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/{id}")
    public UserResponceDto getUserById(@PathVariable("id") Long id){
        return userEntityService.getUserById(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userEntityService.getAllUsers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponceDto addUser(@RequestBody UserRequestDto userRequestDto){
        return userEntityService.addUser(userRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponceDto updateUserById(@PathVariable("id") Long id,@RequestBody UserRequestDto userRequestDto){
        return userEntityService.updateUserById(id,userRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id){
        return userEntityService.deleteUserById(id);
    }
}
