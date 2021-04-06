package com.sshpr.cgnznt.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caseArgument;
    private String caseSolution;

    public TaskCase(String caseArgument, String caseSolution) {
        this.caseArgument = caseArgument;
        this.caseSolution = caseSolution;
    }
}
