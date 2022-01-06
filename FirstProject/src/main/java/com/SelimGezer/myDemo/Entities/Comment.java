package com.SelimGezer.myDemo.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Comment")
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Date commentDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="postMap",foreignKey = @ForeignKey(name = "comment_to_post"))
    private Post postMap;

    @ManyToOne
    @JoinColumn(name = "user_id_2",foreignKey = @ForeignKey(name = "comment_to_user"))
    private User user;

    public Comment(User user,String comment, Date commentDate, Post postMap) {
        this.user=user;
        this.comment = comment;
        this.commentDate = commentDate;
        this.postMap = postMap;
    }
}
