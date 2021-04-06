package com.sshpr.cgnznt.demo.repository;

import com.sshpr.cgnznt.demo.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    boolean existsByRoleName(String name);

    Optional<AccountRole> findByRoleName(String roleName);
}
