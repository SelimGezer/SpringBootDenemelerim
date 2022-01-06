package com.SelimGezer.myDemo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Post")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","comment"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "post_to_user"))
    User user;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "postMap")
   // @JoinColumn(name="comment_id",foreignKey = @ForeignKey(name = "post_to_comment"))
    List<Comment> comment;

    public Post(User user) {
        this.user = user;
    }
}
