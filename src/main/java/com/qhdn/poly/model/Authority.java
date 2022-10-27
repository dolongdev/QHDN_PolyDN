package com.qhdn.poly.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Authorities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"roleId", "userId"})
})
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne  @JoinColumn(name = "roleId")
    private Role role;
}
