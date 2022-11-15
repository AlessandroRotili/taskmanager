package com.generation.taskmanager.interfaces;

import java.util.List;

import com.generation.taskmanager.entities.TaskAssegnato;

public interface IDAOTasksAssegnati
{
	List<TaskAssegnato> read(String query, String...params);
	boolean create(TaskAssegnato t);
	boolean update(TaskAssegnato t);
	boolean delete(int id);
}
