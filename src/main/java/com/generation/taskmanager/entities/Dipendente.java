package com.generation.taskmanager.entities;

import java.util.List;

import com.generation.utility.entities.Entity;

public class Dipendente extends Entity{
	
	private int id;
	private String nome;
	private String seniority;
	private String password;
	private int idProjectOwner;
	private List <TaskAssegnato> taskAssegnati;
		
	
	
	public int getIdProjectOwner() {
		return idProjectOwner;
	}
	public void setIdProjectOwner(int idProjectOwner) {
		this.idProjectOwner = idProjectOwner;
	}
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
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<TaskAssegnato> getTaskAssegnati() {
		return taskAssegnati;
	}
	public void setTaskAssegnati(List<TaskAssegnato> taskAssegnati) {
		this.taskAssegnati = taskAssegnati;
	}
	
	
	
	
	

}
