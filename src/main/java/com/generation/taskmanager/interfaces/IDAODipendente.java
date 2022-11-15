package com.generation.taskmanager.interfaces;

import java.util.List;

import com.generation.taskmanager.entities.Dipendente;

public interface IDAODipendente 
{
	List<Dipendente> read(String query, String... params);
	boolean create(Dipendente d);
	boolean update(Dipendente d);
	boolean delete(int id);
}

