package com.sshpr.cgnznt.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private boolean locked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_user_roles",
            joinColumns = @JoinColumn(name = "account_id"))
    private Set<AccountRole> userRoles;

    public boolean isAdmin() {
        return userRoles.stream()
                .map(AccountRole::getRoleName)
                .anyMatch(s -> s.equals("ADMIN"));
    }
}
