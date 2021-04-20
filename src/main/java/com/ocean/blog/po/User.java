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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity//标识为entity，具备和数据库交互的能力
@Table(name = "t_user")//指定表名
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;//用户类型
    @Temporal(TemporalType.TIMESTAMP)//对应数据库中的时间
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)//对应数据库中的时间
    private Date updateTime;

    //一个User可以有多个blog
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();

}
