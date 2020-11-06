package com.lzb.system.admin.doman;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "lzb_menu")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;
    //标题
    private String title;
    //菜单组件名称
    private String componentName;
    //排序
    private Integer menuSort = 99999;
    //组件路径
    private String component;
    //路由地址
    private String path;
    //菜单类型，目录、菜单、按钮
    private Integer type;
    //权限标识
    private String permission;
    //菜单图标
    private String icon;
    //缓存
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean cache;
    //是否隐藏
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean hidden;
    //上级菜单
    private Long pid;
    //子节点数目
    private Integer subCount = 0;
    //外链菜单
    private Boolean iFrame;
    //创建人
    private String createBy;
    //更新人
    private String updatedBy;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;

    //
//    @JsonIgnore
//    @ManyToMany(mappedBy = "menus")
//    private Set<Role> roles = new HashSet<>();
}
