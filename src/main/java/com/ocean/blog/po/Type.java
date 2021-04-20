package com.ocean.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/*
    这是博客类型的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "不能为空")
    private String name;

    //一个类型中有多个博客
    @OneToMany(mappedBy = "type")//One的一端作为关系被维护端
    private List<Blog> blogs = new ArrayList<>();
}
