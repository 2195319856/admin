package com.lzb.system.admin.doman;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "lzb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    //账号
    private String username;
    //名称
    private String name;
    //密码
    private String password;
    //性别
    private String sex;
    //邮箱
    private String email;
    //电话
    private String tel;
    //头像
    private String avatarPath;
    //是否启用
    private Boolean enabled;
    //是否admin账号
    private Boolean isAdmin = false;
    //修改密码时间
    private String pwdResetTime;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
    //
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "lzb_user_role",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")})
    private Set<Role> role =new HashSet<>();
}
