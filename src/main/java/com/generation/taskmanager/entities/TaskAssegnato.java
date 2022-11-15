package com.generation.taskmanager.entities;

import java.sql.Date;

import com.generation.utility.entities.Entity;

public class TaskAssegnato extends Entity {
	
	private int id;
	private Date scadenza;
	private String stato;
	private String idDipendente;
	private int idTask;
		
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getScadenza() {
		return scadenza;
	}
	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public int getIdTask() {
		return idTask;
	}
	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}
	public String getIdDipendente() {
		return idDipendente;
	}
	public void setIdDipendente(String idDipendente) {
		this.idDipendente = idDipendente;
	}
	
	
	
	

}
