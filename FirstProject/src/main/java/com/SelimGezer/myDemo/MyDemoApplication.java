package com.SelimGezer.myDemo;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MyDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyDemoApplication.class, args);
	}

}
