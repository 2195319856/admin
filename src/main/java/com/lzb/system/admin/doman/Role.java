package com.lzb.system.admin.doman;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "lzb_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    //角色名称
    private String name;
    //描述
    private String description;
    //是否启用
    private Boolean enabled;
    //级别
    private Integer level = 9999;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<>();

    @ManyToMany(targetEntity = Menu.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "lzb_role_menu",joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")},inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "menu_id")})
    private Set<Menu> menus =new HashSet<>();
}
