package com.SelimGezer.Validation.Service;

import com.SelimGezer.Validation.Dao.UserDao;
import com.SelimGezer.Validation.Entities.User;
import com.SelimGezer.Validation.Util.ResponceBodyWithData;

import org.springframework.stereotype.Service;


@Service
public class UserEntityService {

    private final UserDao userDao;

    public UserEntityService(UserDao userDao) {
        this.userDao = userDao;
    }

    public ResponceBodyWithData addUser(User user) {
        ResponceBodyWithData responceBodyWithData =new ResponceBodyWithData();
        User byUsername = userDao.findByUsername(user.getUsername()); //unique alan için direk try-catch yerine sorgulama yapmak daha iyi olduğu söyleniyor.
        if(byUsername!=null){
            responceBodyWithData.message="Failed";
            responceBodyWithData.body="Kulllanılan bir kullanıcı adı seçtiniz! Farklı bir kullanıcı adı giriniz!";
        }else{
            User save = userDao.save(user);
            responceBodyWithData.message="Success";
            responceBodyWithData.body=save;
        }
        return responceBodyWithData;
    }
}
