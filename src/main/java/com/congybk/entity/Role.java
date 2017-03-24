package com.congybk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;
/**
 * @author YNC
 */
@Entity
@Table(name = "role")
public class Role {
    private int id;
    private String name;
    private List<Permission> permissionList;
    public Role() {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(int id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    @JsonIgnore
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
