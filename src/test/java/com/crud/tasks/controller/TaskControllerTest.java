package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig
@WebMvcTest(TrelloController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldGetTasks() throws  Exception
    {
        //Given
        List<Task> tasks = List.of(new Task(1L,"test","test"), new Task(2L,"test2","test2"));
        List<TaskDto> taskDtos = List.of(new TaskDto(1L,"test","test"), new TaskDto(2L,"test2","test2"));

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void shouldGetTask() throws Exception
    {
        //Given
        Task task1 = new Task(1L,"test","test");
        Optional<Task> task = Optional.of(task1);
        TaskDto taskDto = new TaskDto(1L,"test","test");


        when(service.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task1)).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("v1/tasks/taskId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$id", Matchers.is(1L)));
    }

    @Test
    void shouldDeleteTask() throws Exception
    {
        //Given
        Task task = new Task(1L,"test","test");

        service.deleteTask(1L);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$id", Matchers.is(1L)));
    }

    @Test
    void shouldUpdateTask() throws Exception
    {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test","test");
        Task task = new Task(1L,"test","test");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$id", Matchers.is(1L)));
    }

    @Test
    void shouldCreateTask() throws Exception
    {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test","test");
        Task task = new Task(1L,"test","test");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$id", Matchers.is(1L)));
    }
}