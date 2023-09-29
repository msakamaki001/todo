package com.example.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entity.TodoData;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoService {
	@Autowired
	TodoRepository repo;
	
	public List<TodoData> list() {
		return repo.findAll();
	}
	
	public boolean entry(TodoData data) {
		return repo.saveAndFlush(data) != null ? true : false; 
	}
	
	public boolean update(TodoData data) {
		return repo.saveAndFlush(data) != null ? true : false;
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
}
