package com.generation.taskmanager.entities;

import java.util.List;

import com.generation.utility.entities.Entity;

public class ProjectOwner extends Entity{
	
	private int id;
	private String nome;
	private String password;
	private List<Task> task;
	private List<Dipendente> dipendenti;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Task> getTask() {
		return task;
	}
	public void setTask(List<Task> task) {
		this.task = task;
	}
	public List<Dipendente> getDipendenti() {
		return dipendenti;
	}
	public void setDipendenti(List<Dipendente> dipendenti) {
		this.dipendenti = dipendenti;
	}
	
	
	
	

}
