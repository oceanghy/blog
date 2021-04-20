package com.ocean.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    这是一个博客实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity//标识为entity，具备和数据库交互的能力
@Table(name = "t_blog")//指定表名
public class Blog {
    @Id//表名主键
    @GeneratedValue//自动生成sql
    private Long id;//博客id，代表主键

    private String title;//博客标题
    private String content;//博客内容
    private String firstPicture;//博客界面图片
    private String flag;//博客标记，原创/转载/翻译
    private Integer viewTimes;//浏览次数
    private boolean appreciation;//是否开启赞赏
    private boolean shareStatement;//是否开启转载
    private boolean comment;//是否开启评论
    private boolean published;//是否开启版权声明
    private boolean recommend;//是否开启推荐
    @Temporal(TemporalType.TIMESTAMP)//对应数据库中的时间
    private Date createTime;//博客创建时间
    private Date updateTime;//更新时间


    //一个blog只有一个type
    @ManyToOne//many的一端作为关系维护端
    private Type type;

    //一个blog有多个tag
    @ManyToMany(cascade = {CascadeType.PERSIST})//级联新增，当blog增加，对应的tag也增加
    private List<Tag> tags = new ArrayList<>();

    //一个blog只有一个User
    @ManyToOne
    private  User user;

    //一个blog可以有多个Comment
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();



}
