package com.SelimGezer.myDemo.Services;

import com.SelimGezer.myDemo.Dtos.UserRequestDto;
import com.SelimGezer.myDemo.Dtos.UserResponceDto;
import com.SelimGezer.myDemo.Entities.User;
import com.SelimGezer.myDemo.Exception.UserNotFound;
import com.SelimGezer.myDemo.Mapper.UserMapper;
import com.SelimGezer.myDemo.Repository.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService {

    private final UserDao userDao;

    public UserEntityService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserResponceDto getUserById(Long id) {
        User user = findUser(id);
        if(user!=null){
            return UserMapper.toDto(user);
        }else{
            throw new UserNotFound(String.format("Belirtilen %d ye sahip bir kullanıcı bulunamadı!",id));
        }
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public UserResponceDto addUser(UserRequestDto userRequestDto) {
        User user= UserMapper.toEntity(userRequestDto);
        User save = userDao.save(user);
        return UserMapper.toDto(save);
    }

    public UserResponceDto updateUserById(Long id, UserRequestDto userRequestDto) {
        if(findUser(id)!=null){
            User user = UserMapper.toEntity(userRequestDto);
            user.setId(id);
            User save = userDao.save(user);
            return UserMapper.toDto(save);
        }else{
            throw new UserNotFound(String.format("Belirtilen %d ye sahip bir kullanıcı bulunamadı!",id));
        }
    }

    public String deleteUserById(Long id) {
        if(findUser(id)!=null){
            userDao.deleteById(id);
            return new String("Kullanıcı başarı ile silindi!");
        }else{
            throw new UserNotFound(String.format("Belirtilen %d ye sahip bir kullanıcı bulunamadı!",id));
        }
    }


    public User findUser(Long id){
        Optional<User> optionalUser = userDao.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            return null;
        }
    }
}
