package com.generation.taskmanager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.taskmanager.entities.Task;
import com.generation.taskmanager.entities.TaskAssegnato;
import com.generation.taskmanager.interfaces.IDAOTask;
import com.generation.utility.dao.Database;


public class DAOTask implements IDAOTask
{
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private DAOTasksAssegnati dt;
	
	@Override
	public List<Task> read(String query, String... params)
	{
		List<Task> ris = new ArrayList<Task>();
		List<Map<String, String>> righe = db.rows(query, params);
		
		for(Map<String, String> riga : righe)
		{
			List<TaskAssegnato> task = dt.dipendentiAssegnati(Integer.parseInt(riga.get("id")));
			Task d = context.getBean(Task.class, riga, task);
			ris.add(d);
		}
		return ris;		
	}
	
	public List<Task> leggiTutti()
	{
		return read("select * from tasks;");
	}
	
	public List<Task> tasksAssegnati(int idProjectOwner)
	{
		return read("select * from tasks where tasks.idprojectowner = ?", idProjectOwner + "");
	}
	
	public Task cercaPerId(int id)
	{
		//Il try catch è necessario perché una ricerca per numero potrebbe restituire un valore
		//che non esiste. Se cerco il numero 10 su 9 stanze va in IndexOutOfBoundsException
		//quindi io che son furbo restituisco null.
		try
		{
			return read("select * from tasks where tasks.id = ?", id + "").get(0);
		}
		catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}
	@Override
	public boolean create(Task t)
	{
		String query = "insert into tasks (titolo, difficolta, idprojectowner) values (?,?,?);";
		//Per i boolean facciamo un ternario che se è true restituisce 1, altrimenti 0
		return db.update(query, t.getTitolo() + "", t.getDifficolta() + "",t.getIdProjectOwner()+"");
	}
	@Override
	public boolean update(Task t)
	{
		String query = "update tasks set titolo = ?, difficolta = ?, idprojectowner = ? where id = ?;";
		//Per i boolean facciamo un ternario che se è true restituisce 1, altrimenti 0
		return db.update(query, t.getTitolo() + "", t.getDifficolta() + "",t.getIdProjectOwner()+"",t.getId()+"");
	}
	@Override
	public boolean delete(int id)
	{
		String idTask = Integer.toString(id);
		String query = "delete from tasks where id = ?;";
		return db.update(query, idTask);
	}

	

	
}
	
	


