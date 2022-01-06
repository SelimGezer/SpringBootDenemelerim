package com.SelimGezer.myDemo.Configure;

import com.SelimGezer.myDemo.Entities.Comment;
import com.SelimGezer.myDemo.Entities.Like;
import com.SelimGezer.myDemo.Entities.Post;
import com.SelimGezer.myDemo.Entities.User;
import com.SelimGezer.myDemo.Repository.CommentDao;
import com.SelimGezer.myDemo.Repository.LikeDao;
import com.SelimGezer.myDemo.Repository.PostDao;
import com.SelimGezer.myDemo.Repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

//@Configuration
public class InialValue implements CommandLineRunner {

    @Autowired
    UserDao userDao;
    @Autowired
    PostDao postDao;
    @Autowired
    LikeDao likeDao;
    @Autowired
    CommentDao commentDao;

    @Override
    public void run(String... args) throws Exception {

        //Userları yükle
        User user=new User("Selim","Gezer","27selimgezer@gmail.com");
        User user2=new User("Neşe","Yetkin","neşe@gmail.com");
        User user3=new User("Alican","Tamam","alican@gmail.com");
        User user4=new User("Alexandra","Peggy","alexandra@gmail.com");
        User user5=new User("Nicolas","Page","nicolas@gmail.com");

        userDao.saveAll(List.of(user,user2,user3,user4,user5));

        //Postları yükle
        Post post1=new Post(user);
        Post post2=new Post(user);
        Post post3=new Post(user2);
        Post post4=new Post(user3);
        Post post5=new Post(user4);

        postDao.saveAll(List.of(post1,post2,post3,post4,post5));

        //Likeları yükle
        Like like1=new Like(user2,post1);
        Like like2=new Like(user3,post1);
        Like like3=new Like(user4,post1);
        Like like4=new Like(user5,post2);
        Like like5=new Like(user3,post2);
        Like like6=new Like(user3,post3);
        Like like7=new Like(user4,post3);

        likeDao.saveAll(List.of(like1,like2,like3,like4,like5,like6,like7));

        //Commentleri yükle
        Comment comment=new Comment(user2,"Efssane olmuş", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),post1);
        Comment comment1=new Comment(user3,"İdare eder", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),post2);
        Comment comment2=new Comment(user4,"Pek kullanışlı değil", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),post2);
        Comment comment3=new Comment(user3,"Çok güzel", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),post5);

        commentDao.saveAll(List.of(comment,comment1,comment2,comment3));
    }

}
