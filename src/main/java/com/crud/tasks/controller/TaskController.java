package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {

    private final TaskService service;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        final List<Task> allTasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(allTasks);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        final Task task = service.getTask(taskId)
                .orElseThrow(TaskNotFoundException::new);

        return taskMapper.mapToTaskDto(task);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam Long taskId) throws TaskNotFoundException {
        service.deleteTask(taskId)
                .orElseThrow(TaskNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        final Task inputTask = taskMapper.mapToTask(taskDto);
        final Task updatedTask = service.saveTask(inputTask);

        return taskMapper.mapToTaskDto(updatedTask);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}
