package com.generation.taskmanager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.taskmanager.entities.TaskAssegnato;
import com.generation.taskmanager.interfaces.IDAOTasksAssegnati;
import com.generation.utility.dao.Database;

public class DAOTasksAssegnati implements IDAOTasksAssegnati {

	@Autowired
	private Database db;

	@Autowired
	private ApplicationContext context;
	@Override
	public List<TaskAssegnato> read(String query, String... params)
	{
		List<TaskAssegnato> ris = new ArrayList<TaskAssegnato>();
		List<Map<String, String>> righe = db.rows(query, params);

		for(Map<String, String> riga : righe)
		{
			//Un altro esempio di getBean() è quello che riceve il nome della classe insieme alla mappa.
			//In questo caso, siccome gli stiamo passando anche il nome della classe, non è necessario
			//castare perché l'oggetto che viene creato è del tipo della classe passata come parametro.
			TaskAssegnato p = context.getBean(TaskAssegnato.class,riga);
			ris.add(p);
		}
		return ris;
	}

	public List<TaskAssegnato> leggiTutti()
	{
		return read("select * from tasksassegnati;");
	}
	//Creiamo un metodo che restituisca la lista delle prenotazioni legate alla stanza il cui numero
	//(che rappresenta la chiave primaria in SQL) viene passato come parametro
	public TaskAssegnato cercaPerId(int id)
	{
		//Il try catch è necessario perché una ricerca per numero potrebbe restituire un valore
		//che non esiste. Se cerco il numero 10 su 9 stanze va in IndexOutOfBoundsException
		//quindi io che son furbo restituisco null.
		try
		{
			return read("select * from tasksassegnati where tasksassegnati.id = ?", id + "").get(0);
		}
		catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}
	

	public List<TaskAssegnato> tasksAssegnati(int idDipendente)
	{
		return read("select * from tasksassegnati where tasksassegnati.iddipendente = ?", idDipendente + "");
	}
	public List<TaskAssegnato> dipendentiAssegnati(int idTask)
	{
		return read("select * from tasksassegnati where tasksassegnati.idtask = ?", idTask + "");
	}
	@Override
	public boolean create(TaskAssegnato t)
	{
		String query = "insert into tasksassegnati (scadenza,stato,iddipendente,idtask) values (?,?,?,?);";
		//Per i boolean facciamo un ternario che se è true restituisce 1, altrimenti 0
		return db.update(query, t.getScadenza() + "", 
				t.getStato() + "",
				t.getIdDipendente() +"", //.getIdDipendente().split(" ")[4]+"",
				t.getIdTask()+"");
	}
	@Override
	public boolean update(TaskAssegnato t)
	{
		String query = "update tasksassegnati set scadenza = ?, stato = ?, idtask = ?  where idtask = ?;";
	//Per i boolean facciamo un ternario che se è true restituisce 1, altrimenti 0
		return db.update(query, t.getScadenza() + "", t.getStato() + "",t.getIdTask()+"",t.getIdTask()+"");
	}

	@Override
	public boolean delete(int id) {
		String idTaskAssegnato = Integer.toString(id);
		String query = "delete from tasksassegnati where id = ?;";
		return db.update(query, idTaskAssegnato);
	}
	

}
