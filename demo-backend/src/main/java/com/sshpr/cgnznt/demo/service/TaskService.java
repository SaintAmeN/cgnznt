package com.sshpr.cgnznt.demo.service;

import com.sshpr.cgnznt.demo.model.Task;
import com.sshpr.cgnznt.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: amen
 * Date: 06/04/2021
 * Project: demo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAll(){
        return taskRepository.findAll();
    }
}
