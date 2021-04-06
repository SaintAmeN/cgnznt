package com.sshpr.cgnznt.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccountScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private Account user;

    private Long score;

    @Enumerated(EnumType.STRING)
    private LanguageChoice languageChoice;

    private String submissionCode;

    @ManyToOne
    private Task task;
}
