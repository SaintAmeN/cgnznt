package com.sshpr.cgnznt.demo.component;

import com.sshpr.cgnznt.demo.model.Account;
import com.sshpr.cgnznt.demo.model.AccountRole;
import com.sshpr.cgnznt.demo.repository.AccountRepository;
import com.sshpr.cgnznt.demo.repository.AccountRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${default.roles}")
    private String[] defaultRoles;

    @Value("${default.admin.password:admin}")
    private String defaultAdminPassword;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Set<String> existingRoles = accountRoleRepository.findAll().stream().map(AccountRole::getRoleName).collect(Collectors.toSet());
        for (String role : defaultRoles) {
            log.info("Checking role: " + role + " in set: " + existingRoles);
            if (!existingRoles.contains(role)) {
                addRole(role);
            }
        }

        addUser("admin", defaultAdminPassword, "USER", "ADMIN");
        addUser("user", "user", "USER");
    }

    private void addUser(String username, String password, String... roles) {
        if (!accountRepository.existsByUsername(username)) {
            Account account = new Account();

            account.setUsername(username);
            account.setPassword(passwordEncoder.encode(password));

            Set<AccountRole> userRoles = findRoles(roles);
            account.setUserRoles(userRoles);

            accountRepository.save(account);
        }
    }

    private Set<AccountRole> findRoles(String[] roles) {
        Set<AccountRole> accountRoles = new HashSet<>();
        for (String role : roles) {
            accountRoleRepository.findByRoleName(role).ifPresent(accountRoles::add);
        }
        return accountRoles;
    }

    private void addRole(String roleToCreate) {
        if (!accountRoleRepository.existsByRoleName(roleToCreate)) {
            AccountRole accountRole = new AccountRole();
            accountRole.setRoleName(roleToCreate);

            accountRoleRepository.save(accountRole);
        }
    }
}
