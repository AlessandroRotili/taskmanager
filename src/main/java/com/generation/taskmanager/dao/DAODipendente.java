package com.generation.taskmanager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.taskmanager.entities.Dipendente;
import com.generation.taskmanager.entities.TaskAssegnato;
import com.generation.taskmanager.interfaces.IDAODipendente;
import com.generation.utility.dao.Database;

public class DAODipendente implements IDAODipendente
{
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private DAOTasksAssegnati dt;
	
	
	@Override
	public List<Dipendente> read(String query, String... params)
	{
		List<Dipendente> ris = new ArrayList<Dipendente>();
		List<Map<String, String>> righe = db.rows(query, params);
		
		for(Map<String, String> riga : righe)
		{
			List<TaskAssegnato> task = dt.tasksAssegnati(Integer.parseInt(riga.get("id")));
			Dipendente d = context.getBean(Dipendente.class, riga, task);
			ris.add(d);
		}
		return ris;		
	}
	
	public List<Dipendente> leggiTutti()
	{
		return read("select * from dipendenti;");
	}
	
	public List<Dipendente> dipendentiAssegnati(int idProjectOwner)
	{
		return read("select * from dipendenti where dipendenti.idprojectowner = ?", idProjectOwner + "");
	}
	
	public Dipendente cercaPerId(int id)
	{
		//Il try catch è necessario perché una ricerca per numero potrebbe restituire un valore
		//che non esiste. Se cerco il numero 10 su 9 stanze va in IndexOutOfBoundsException
		//quindi io che son furbo restituisco null.
		try
		{
			return read("select * from dipendenti where dipendenti.id = ?", id + "").get(0);
		}
		catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}
	@Override
	public boolean create(Dipendente d)
	{
		String query = "insert into dipendenti (nome, seniority, password, idprojectowner) values (?,?,md5(?),?);";
		//Per i boolean facciamo un ternario che se è true restituisce 1, altrimenti 0
		return db.update(query, d.getNome() + "", d.getSeniority() + "",d.getPassword()+"",d.getIdProjectOwner()+"");
	}
	@Override
	public boolean update(Dipendente d)
	{
		String query = "update dipendenti set nome = ?, seniority = ?,password = md5(?),idprojectowner = ? where id = ?;";
		//Per i boolean facciamo un ternario che se è true restituisce 1, altrimenti 0
		return db.update(query, d.getNome() + "", d.getSeniority() + "",d.getPassword()+"",d.getIdProjectOwner()+"",d.getId()+"");
	}
	@Override
	public boolean delete(int id)
	{
		String query = "delete from dipendenti where id = ?;";
		return db.update(query, id+"");
	}
	public boolean login(String username, String password)
	{
		//Per verificare se la persona che vuole loggarsi � registrata lancio una query che pu� tornare solo due valori:
		//0 se la persona non esiste perch� significa che count(*) restituisce 0
		//Un numero maggiore di zero se la persona esiste. Idealmente dovrebbe tornare 1
		String query = "select count(*) as n from dipendenti where nome = ? and password = md5(?);";
		//Con la tabella che mi torna la query creo una mappa riga
		Map<String, String> riga = Config.DB.row(query, username, password);
		//Dichiaro una variabile count che avr� come valore il valore della chiave "n" ossia l'alias della tabella sul db.
		//Il valore di count, e quindi di n sar� 0 oppure 1
		int count = Integer.parseInt(riga.get("n"));
		//Scrivere il return in questo modo � come dire che se il valore di count � > 0 ritorna true
		//altrimenti false.
		//Potremmo scrivere anche if(count > 0) return true else return false;
		//In pratica il return � il risultato di (count > 0)
		return count > 0;
	}
}
