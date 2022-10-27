package com.qhdn.poly.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "role")
    List<Authority> authorities;
}
