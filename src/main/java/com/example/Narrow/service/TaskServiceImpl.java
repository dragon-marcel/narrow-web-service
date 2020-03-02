package com.example.Narrow.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.Task;
import com.example.Narrow.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTaskByIdUser(Long id) {

		return em.createQuery("from Task where idUser = :user_id and idStatus !=:status_id").setParameter("user_id", id)
				.setParameter("status_id", 3).getResultList();
	}
	@Override
	 public int getCountTaskByUserId(Long id) {
		Long count = (Long)em.createQuery("select count(*) from Task where idUser = :user_id and idStatus =:status_id").setParameter("user_id", id)
			.setParameter("status_id", 1).getSingleResult();
		return count.intValue();
		
	 }

	@Override
	public void save(Task task) {
		task.setCreatedDate(new Date());
		taskRepository.save(task);

	}

	@Override
	public Task completedTask(Long id) {
		Task task = null;
		if (id != null) {
			task = getTaskById(id);
			if (task != null) {
				task.setIdStatus(2);
				taskRepository.save(task);
			}
		}
		return task;

	}

	@Override
	public Task removeTask(Long id) {
		Task task = null;
		if (id != null) {
			task = getTaskById(id);
			if (task != null) {
				task.setIdStatus(3);
				taskRepository.save(task);
			}
		}
		return task;

	}

	@Override
	public Task getTaskById(Long id) {
		return taskRepository.findById(id).orElse(null);
	}

	@Override
	public Task toDoTask(Long id) {
		Task task = null;
		if (id != null) {
			task = getTaskById(id);
			if (task != null) {
				task.setIdStatus(1);
				taskRepository.save(task);
			}
		}
		return task;
	}

	@Override
	public Task changeNotificationEmial(Long id) {
		Task task = null;
		if (id != null) {
			task = getTaskById(id);
			if (task != null) {
				if (task.isEmail() == false) {
					task.setEmail(true);
				} else {
					task.setEmail(false);
				}
				taskRepository.save(task);
			}
		}
		return task;
	}

	@Override
	public List<Task> completedAll(Long id) {
		List<Task> tasks = getTaskByIdUser(id);
		tasks = tasks.stream().filter(task -> task.getIdStatus() == 1).collect(Collectors.toList());
		for (int a = 0; a < tasks.size(); a++) {
			Task task = tasks.get(a);
			task.setIdStatus(2);
			taskRepository.save(task);
		}
		return tasks;
	}

	@Override
	public List<Task> clearAllTask(Long id) {
		List<Task> tasks = getTaskByIdUser(id);
		tasks = tasks.stream().filter(task -> task.getIdStatus() == 2).collect(Collectors.toList());
		for (int a = 0; a < tasks.size(); a++) {
			Task task = tasks.get(a);
			task.setIdStatus(3);
			taskRepository.save(task);
		}
		return tasks;
	}

}