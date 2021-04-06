package com.sshpr.cgnznt.demo.component;

import com.sshpr.cgnznt.demo.model.Account;
import com.sshpr.cgnznt.demo.model.AccountRole;
import com.sshpr.cgnznt.demo.model.Task;
import com.sshpr.cgnznt.demo.model.TaskCase;
import com.sshpr.cgnznt.demo.repository.AccountRepository;
import com.sshpr.cgnznt.demo.repository.AccountRoleRepository;
import com.sshpr.cgnznt.demo.repository.TaskCaseRepository;
import com.sshpr.cgnznt.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final AccountRepository accountRepository;
    private final TaskRepository taskRepository;
    private final TaskCaseRepository taskCaseRepository;
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

        addTask("Fibonacci", "The Fibonacci sequence appears in nature all around us, in the arrangement of seeds in a sunflower and the spiral of a nautilus for example.\n" +
                "\n" +
                "The next number is found by adding up the two numbers before it:\n" +
                "\n" +
                "the 2 is found by adding the two numbers before it (1+1),\n" +
                "the 3 is found by adding the two numbers before it (1+2),\n" +
                "the 5 is (2+3),\n" +
                "and so on! \n" +
                "Your task is to create an app, which takes N as parameter, and returns N-th value in Fibonacci sequence\n",
                Arrays.asList(new TaskCase("0", "0"), new TaskCase("1", "1")
                        , new TaskCase("12", "144"), new TaskCase("30", "832040")));
    }

    private void addTask(String name, String description, List<TaskCase> tasks) {
        if(!taskRepository.existsByName(name)){
            Set<TaskCase> tasksSet = saveTaskCase(tasks);
            Task t = new Task(name, description, tasksSet);
            taskRepository.save(t);
        }
    }

    private Set<TaskCase> saveTaskCase(List<TaskCase> tasks) {
        Set<TaskCase> taskCases = new HashSet<>();
        for (TaskCase task : tasks) {
            taskCases.add(taskCaseRepository.save(task));
        }
        return taskCases;
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
