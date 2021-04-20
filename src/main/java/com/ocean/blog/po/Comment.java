package com.ocean.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="t_comment")
/*
    这是博客评论的实体类
 */
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;//头像图片
    @Temporal(TemporalType.TIMESTAMP)//对应数据库中的时间
    private Date createTime;

    //一个Comment只能对应一个博客
    @ManyToOne
    private Blog blog;

    //评论的自关联关系
    //父评论包含多个回复评论
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replay = new ArrayList<>();
    //回复只有一个父评论
    @ManyToOne
    private Comment parentComment;


}
