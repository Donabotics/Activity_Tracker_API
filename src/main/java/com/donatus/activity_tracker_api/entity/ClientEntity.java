package com.donatus.activity_tracker_api.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name", length = 25)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 68)
    private String password;

    @Column(name = "occupation",nullable = false , length = 100)
    private String occupation;

    @Column(name = "address", length = 100)
    private String address;

    @OneToMany(mappedBy = "clientEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskEntity> tasksList;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "client_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Roles> roles = new ArrayList<>();


//    public void addRole(Roles role){
//        if (roles == null){
//            roles = new ArrayList<>();
//        }
//
//        roles.add(role);
//    }

    public void addTask(TaskEntity task){
        if (tasksList == null){
            tasksList = new ArrayList<>();
        }

        tasksList.add(task);

        task.setClientEntity(this);

    }
}
