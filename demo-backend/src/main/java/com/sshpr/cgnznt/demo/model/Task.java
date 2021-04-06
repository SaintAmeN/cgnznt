package com.sshpr.cgnznt.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @OneToMany
    private Set<TaskCase> cases;

    @OneToMany(mappedBy = "task")
    private Set<AccountScore> scores;

    public Task(String name, String description, Set<TaskCase> cases) {
        this.name = name;
        this.description = description;
        this.cases = cases;
    }
}
