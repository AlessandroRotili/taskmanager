package com.generation.taskmanager;
import java.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.generation.taskmanager.dao.DAODipendente;
import com.generation.taskmanager.dao.DAOProjectOwner;
import com.generation.taskmanager.dao.DAOTask;
import com.generation.taskmanager.dao.DAOTasksAssegnati;
import com.generation.taskmanager.entities.Dipendente;
import com.generation.taskmanager.entities.ProjectOwner;
import com.generation.taskmanager.entities.Task;
import com.generation.taskmanager.entities.TaskAssegnato;
import com.generation.utility.dao.Database;

@Configuration
public class Context
{

	@Bean
	@Scope("singleton")	
	public Database db()
	{
		return new Database("taskmanager","root","root");
	}
	
	
	@Bean
	@Scope("prototype")
	public Dipendente dipendenteMappa(Map<String, String> riga, List <TaskAssegnato> taskAssegnati)
	{
		Dipendente d = new Dipendente();
		d.fromMap(riga);
		d.setTaskAssegnati(taskAssegnati);
		return d;
	}
	
	@Bean
	@Scope("prototype")
	public TaskAssegnato taskAssegnatoMappa(Map<String, String> riga)
	{
		TaskAssegnato p = new TaskAssegnato();
		p.fromMap(riga);
		return p;
	}
	
	@Bean
	@Scope("prototype") 
	public ProjectOwner projectOwnerMappa(Map<String, String> riga,List<Task>tasks,List<Dipendente>dipendenti)
	{
		ProjectOwner p = new ProjectOwner();
		p.fromMap(riga);
		p.setTask(tasks);
		p.setDipendenti(dipendenti);
		return p;
	}
	
	@Bean
	@Scope("prototype")
	public Task TaskVuoto()
	{
		return new Task();
	}
	
	@Bean
	@Scope("prototype")
	@Primary
	public Task taskMappa(Map<String, String> riga,List<TaskAssegnato>dipendenti)
	{
		Task t = new Task();
		t.fromMap(riga);
		t.setDipendentiAssegnati(dipendenti);
		return t;
	}
	
	@Bean
	public DAODipendente daoDipendente()
	{
		return new DAODipendente();
	}
	
	@Bean
	public DAOProjectOwner daoProjectOwner()
	{
		return new DAOProjectOwner();
	}
	
	@Bean
	public DAOTask daoTask()
	{
		return new DAOTask();
	}
	
	@Bean
	public DAOTasksAssegnati daoTasksAssegnati()
	{
		return new DAOTasksAssegnati();
	}
}