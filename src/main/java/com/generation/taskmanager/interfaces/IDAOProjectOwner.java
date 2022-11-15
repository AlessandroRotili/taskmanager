package com.generation.taskmanager.interfaces;

import java.util.List;

import com.generation.taskmanager.entities.ProjectOwner;

public interface IDAOProjectOwner 
{
	List<ProjectOwner> read(String query, String...params);
	boolean create(ProjectOwner p);
	boolean update(ProjectOwner p);
	boolean delete(int id);
}

