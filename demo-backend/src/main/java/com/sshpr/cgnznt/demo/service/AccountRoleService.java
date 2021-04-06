package com.sshpr.cgnznt.demo.service;

import com.sshpr.cgnznt.demo.model.AccountRole;
import com.sshpr.cgnznt.demo.repository.AccountRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountRoleService {
    private final AccountRoleRepository accountRoleRepository;

    public List<AccountRole> getAll() {
        return accountRoleRepository.findAll();
    }
}
