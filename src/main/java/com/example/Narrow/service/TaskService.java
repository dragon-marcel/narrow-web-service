package com.example.Narrow.service;

import java.util.List;

import com.example.Narrow.model.Task;

public interface TaskService {
	public List<Task> getTaskByIdUser(Long id);

	public void save(Task task);

	public Task completedTask(Long id);

	public Task removeTask(Long id);

	public Task toDoTask(Long id);

	public Task changeNotificationEmial(Long id);

	public Task getTaskById(Long id);

	public List<Task> completedAll(Long id);

	public List<Task> clearAllTask(Long id);

	public int getCountTaskByUserId(Long id);

}