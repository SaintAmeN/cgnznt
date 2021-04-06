package com.sshpr.cgnznt.demo.repository;

import com.sshpr.cgnznt.demo.model.Task;
import com.sshpr.cgnznt.demo.model.TaskCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */
@Repository
public interface TaskCaseRepository extends JpaRepository<TaskCase, Long> {
}
