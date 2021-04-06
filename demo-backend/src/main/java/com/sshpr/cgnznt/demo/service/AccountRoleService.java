package com.sshpr.cgnznt.demo.service;

import com.sshpr.cgnznt.demo.model.AccountRole;
import com.sshpr.cgnznt.demo.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRoleService {
    @Autowired
    private AccountRoleRepository accountRoleRepository;

    public List<AccountRole> getAll() {
        return accountRoleRepository.findAll();
    }
}
