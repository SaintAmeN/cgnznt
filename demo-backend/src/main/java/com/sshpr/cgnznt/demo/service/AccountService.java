package com.sshpr.cgnznt.demo.service;

import com.sshpr.cgnznt.demo.model.Account;
import com.sshpr.cgnznt.demo.model.AccountRole;
import com.sshpr.cgnznt.demo.model.dto.UserRegistrationRequest;
import com.sshpr.cgnznt.demo.repository.AccountRepository;
import com.sshpr.cgnznt.demo.repository.AccountRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${default.user.roles:USER}")
    private String[] defaultUserRegisterRoles;

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public void removeAccount(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            if (!account.isAdmin()) {
                accountRepository.delete(account);
            }
        }
    }

    public void toggleLock(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            account.setLocked(!account.isLocked());

            accountRepository.save(account);
        }
    }

    public boolean register(UserRegistrationRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            return false;
        }

        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        account.setUserRoles(findRolesByName(defaultUserRegisterRoles));

        accountRepository.save(account);

        return true;
    }

    public Set<AccountRole> findRolesByName(String... roles) {
        Set<AccountRole> accountRoles = new HashSet<>();
        for (String role : roles) {
            accountRoleRepository.findByRoleName(role).ifPresent(accountRoles::add);
        }
        return accountRoles;
    }

    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

    public void updateRoles(Long accountId, HttpServletRequest request) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            Map<String, String[]> parameterMap = request.getParameterMap();
            Set<AccountRole> accountRoles = new HashSet<>();

            for (String key : parameterMap.keySet()) {
                accountRoleRepository.findByRoleName(key).ifPresent(accountRoles::add);
            }

            account.setUserRoles(accountRoles);
            accountRepository.save(account);
        }
    }
}
