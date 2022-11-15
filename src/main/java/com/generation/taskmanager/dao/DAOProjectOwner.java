package com.generation.taskmanager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.taskmanager.entities.Dipendente;
import com.generation.taskmanager.entities.ProjectOwner;
import com.generation.taskmanager.entities.Task;
import com.generation.taskmanager.interfaces.IDAOProjectOwner;
import com.generation.utility.dao.Database;

public class DAOProjectOwner implements IDAOProjectOwner 
{
	
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private DAOTask dt;
	
	@Autowired
	private DAODipendente dd;
	
	@Override
	public List<ProjectOwner> read(String query, String... params)
	{
		List<ProjectOwner> ris = new ArrayList<ProjectOwner>();
		List<Map<String, String>> righe = db.rows(query, params);
		
		for(Map<String, String> riga : righe)
		{
			List<Task> task = dt.tasksAssegnati(Integer.parseInt(riga.get("id")));
			List<Dipendente> dipendenti = dd.dipendentiAssegnati(Integer.parseInt(riga.get("id")));
			
			ProjectOwner p = (ProjectOwner) context.getBean("projectOwnerMappa",riga,task,dipendenti);
			ris.add(p);
		}
		return ris;		
	}
	
	
	public List<ProjectOwner> leggiTutti()
	{
		return read("select * from projectowners;");
	}
	public ProjectOwner cercaPerId(int id)
	{
		//Il try catch è necessario perché una ricerca per numero potrebbe restituire un valore
		//che non esiste. Se cerco il numero 10 su 9 stanze va in IndexOutOfBoundsException
		//quindi io che son furbo restituisco null.
		try
		{
			return read("select * from projectowners where projectowners.id = ?", id + "").get(0);
		}
		catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}
	
	@Override
	public boolean create(ProjectOwner p)
	{
		String query = "insert into projectowners (nome, password) values (?,md5(?));";
		//Per i boolean facciamo un ternario che se è true restituisce 1, altrimenti 0
		return db.update(query, p.getNome() + "",p.getPassword());
	}
	@Override
	public boolean update(ProjectOwner p)
	{
		String query = "update projectowners set nome = ?, password = md5(?) where id = ?;";
		//Per i boolean facciamo un ternario che se è true restituisce 1, altrimenti 0
		return db.update(query, p.getNome() + "", p.getPassword() + "",p.getId()+"");
	}
    @Override
	public boolean delete(int id)
	{
		String query = "delete from projectowners where id = ?;";
		return db.update(query, id+"");
	}
    public boolean login(String username, String password)
	{
		//Per verificare se la persona che vuole loggarsi � registrata lancio una query che pu� tornare solo due valori:
		//0 se la persona non esiste perch� significa che count(*) restituisce 0
		//Un numero maggiore di zero se la persona esiste. Idealmente dovrebbe tornare 1
		String query = "select count(*) as n from projectowners where projectowners.nome = ? and projectowners.password = md5(?);";
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
