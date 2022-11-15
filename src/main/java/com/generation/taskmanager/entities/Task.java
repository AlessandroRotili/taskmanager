package com.generation.taskmanager.entities;

import java.util.List;

import com.generation.utility.entities.Entity;

public class Task extends Entity{
	
	private int id;
	private String titolo;
	private String difficolta;
	private int idProjectOwner;
	private List<TaskAssegnato> dipendentiAssegnati;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDifficolta() {
		return difficolta;
	}
	public void setDifficolta(String difficolta) {
		this.difficolta = difficolta;
	}
	public int getIdProjectOwner() {
		return idProjectOwner;
	}
	public void setIdProjectOwner(int idProjectOwner) {
		this.idProjectOwner = idProjectOwner;
	}
	public List<TaskAssegnato> getDipendentiAssegnati() {
		return dipendentiAssegnati;
	}
	public void setDipendentiAssegnati(List<TaskAssegnato> dipendentiAssegnati) {
		this.dipendentiAssegnati = dipendentiAssegnati;
	}
	
	
	
	
}
