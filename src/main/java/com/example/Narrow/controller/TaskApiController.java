package com.example.Narrow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Narrow.model.Task;
import com.example.Narrow.service.TaskService;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskApiController {

	@Autowired
	private TaskService taskService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<List<Task>> getTasks(@PathVariable Long id) {

		List<Task> task = taskService.getTaskByIdUser(id);
		return new ResponseEntity<List<Task>>(task, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Task> addTask(@RequestBody Task task) {

		taskService.save(task);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@PutMapping(value = "/completed/{id}")
	public ResponseEntity<?> completedTask(@PathVariable Long id) {

		Task task = taskService.completedTask(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@PutMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {

		Task task = taskService.removeTask(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@PutMapping(value = "/todo/{id}")
	public ResponseEntity<?> toDoTask(@PathVariable Long id) {

		Task task = taskService.toDoTask(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@PutMapping(value = "/notification/{id}")
	public ResponseEntity<?> changeNotificationEmial(@PathVariable Long id) {

		Task task = taskService.changeNotificationEmial(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@PutMapping(value = "/clearAll/{id}")
	public ResponseEntity<List<Task>> clearAllTask(@PathVariable Long id) {

		List<Task> task = taskService.clearAllTask(id);
		return new ResponseEntity<List<Task>>(task, HttpStatus.OK);
	}

	@PutMapping(value = "/completedAll/{id}")
	public ResponseEntity<List<Task>> completedAll(@PathVariable Long id) {

		List<Task> task = taskService.completedAll(id);
		return new ResponseEntity<List<Task>>(task, HttpStatus.OK);
	}

}