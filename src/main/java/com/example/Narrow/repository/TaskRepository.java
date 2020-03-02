package com.example.Narrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Narrow.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}