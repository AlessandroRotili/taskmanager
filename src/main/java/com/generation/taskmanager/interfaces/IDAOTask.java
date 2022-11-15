package com.generation.taskmanager.interfaces;

import java.util.List;

import com.generation.taskmanager.entities.Task;

public interface IDAOTask 
{
	List<Task> read(String query, String... params);
	boolean create(Task t);
	boolean update(Task t);
	boolean delete(int id);
}

