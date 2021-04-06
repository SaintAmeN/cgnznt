package com.sshpr.cgnznt.demo.repository;

import com.sshpr.cgnznt.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByName(String name);
}
