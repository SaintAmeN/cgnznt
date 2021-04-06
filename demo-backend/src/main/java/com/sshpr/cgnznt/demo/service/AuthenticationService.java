package com.sshpr.cgnznt.demo.service;

import com.sshpr.cgnznt.demo.model.Account;
import com.sshpr.cgnznt.demo.model.AccountRole;
import com.sshpr.cgnznt.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            String[] roles = account.getUserRoles()
                    .stream()
                    .map(AccountRole::getRoleName)
                    .toArray(String[]::new);

            return User.builder()
                    .accountLocked(account.isLocked())
                    .username(account.getUsername())
                    .password(account.getPassword())
                    .roles(roles)
                    .build();
        }
        throw new UsernameNotFoundException("User not found.");
    }
}
