package com.generation.taskmanager.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generation.taskmanager.dao.DAODipendente;
import com.generation.taskmanager.dao.DAOProjectOwner;
import com.generation.taskmanager.dao.DAOTask;
import com.generation.taskmanager.entities.ProjectOwner;
import com.generation.taskmanager.entities.Task;
import com.google.gson.Gson;

@Controller
@RequestMapping("/tasks")
public class TaskController 
{
	
	@Autowired
	private DAOTask dd;
	
	@Autowired
	private DAODipendente dDip;
	
	@Autowired
	private DAOProjectOwner dPO;
	
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("/test")	
	@ResponseBody
	public String test()
	{
		String ris = "";
		for(Task d : dd.leggiTutti())
			ris += d.toMap() + "<br>";
		return ris;
	}
	
	@GetMapping("/json")
	@ResponseBody
	public String stampaTask()
	{
		Gson gson = new Gson();
		return gson.toJson(dd.leggiTutti());
	}
	
	@GetMapping("/elencotaskd")	//localhost:8080/stanze/elenco
	public String elencod(Model model)
	{
		/* addAttribute() è un metodo dell'oggetto Model di Spring che permette di inserire qualcosa
		 * nello scatolone etichettandolo:
		 * ds.leggiTutti() è ciò che noi mettiamo nel Model ossia la List<Stanza>
		 * "elencoStanze" è l'etichetta che potremo richiamare per accedere alla List<Stanza>
		 * dalla pagina JSP che sarà in grado di leggere il model.
		 * Quale sarà la pagina JSP che sarà in grado di leggere il model? "elenco.jsp"*/
		model.addAttribute("elencoTasks", dd.leggiTutti());
		//Se ci fate caso addAttribute() ricorda molto una mappa: chiave/valore
		//dove la chiave è l'etichetta e il valore è ciò che assegnamo alla chiave.
		
		//Il mapping non ritorna una pagina html ma una pagina JSP che andremo a creare
		//nella cartella webapp/stanze/elenco.jsp
		return "elencotaskd.jsp";
		//Spring non è nativamente in grado di compilare la JSP ma deve importare la DEPENDENCY
		//<dependency>
        //	<groupId>org.apache.tomcat.embed</groupId>
		//	<artifactId>tomcat-embed-jasper</artifactId>
		//	<scope>provided</scope>
		//</dependency>
	}
	@GetMapping("/elencotaskp")	//localhost:8080/stanze/elenco
	public String elencop(Model model)
	{
		/* addAttribute() è un metodo dell'oggetto Model di Spring che permette di inserire qualcosa
		 * nello scatolone etichettandolo:
		 * ds.leggiTutti() è ciò che noi mettiamo nel Model ossia la List<Stanza>
		 * "elencoStanze" è l'etichetta che potremo richiamare per accedere alla List<Stanza>
		 * dalla pagina JSP che sarà in grado di leggere il model.
		 * Quale sarà la pagina JSP che sarà in grado di leggere il model? "elenco.jsp"*/
		model.addAttribute("elencoTasks", dd.leggiTutti());
		//Se ci fate caso addAttribute() ricorda molto una mappa: chiave/valore
		//dove la chiave è l'etichetta e il valore è ciò che assegnamo alla chiave.
		
		//Il mapping non ritorna una pagina html ma una pagina JSP che andremo a creare
		//nella cartella webapp/stanze/elenco.jsp
		return "elencotaskp.jsp";
		//Spring non è nativamente in grado di compilare la JSP ma deve importare la DEPENDENCY
		//<dependency>
        //	<groupId>org.apache.tomcat.embed</groupId>
		//	<artifactId>tomcat-embed-jasper</artifactId>
		//	<scope>provided</scope>
		//</dependency>
	}
	
	
	@GetMapping("/taskdaassegnare")
	public String taskDaAssegnare(	@RequestParam("id") int idTask, Model modelTask, Model modelDipendenti)
	{
		//Grazie all'id viene eseguita una ricerca per trovare il libro che si vuole modificare attraverso il metodo cercaPerId di DAOLibri
		Task t = dd.cercaPerId(idTask);
		
		if(t == null)
		{
			//Se non viene trovato il libro, l'utente viene riportato sulla pagina dell'elenco.
			System.out.println("T == null");
			return "elencotaskp.jsp";
		}
		else
		{
			//Una volta trovato il libro da modificare, viene inserito all'interno del model con l'attributo libro
			//Successivamente l'utente viene riportato alla pagina dettagliLibroModifica.jsp insieme al model contenente il libro da modificare.
			modelTask.addAttribute("task", t);
			modelDipendenti.addAttribute("elencoDipendenti", dDip.leggiTutti());
			System.out.println("prima di return formassegnatask");
			return "formassegnatask.jsp";
			
		}
	}
	@GetMapping("/formnuovotask")
	public String formnuovo(Model model)
	{
		model.addAttribute("elencoProjectOwners", dPO.leggiTutti());
		return "formnuovotask.jsp";
	}
	
	@GetMapping("/nuovotask")
	public String nuovo(@RequestParam Map<String, String> parametri, Model modelPO)
	{
		//Per poter creare la Stanza abbiamo bisogno del bean. Siccome siamo in un metodo 
		//dobbiamo fare riferimento a ApplicationContext
		Task s = context.getBean(Task.class, parametri, null);
		//Stanza.class indica la classe dell'oggetto che voglio creare
		//parametri sono la mappa che mando alla factory per assegnare alle proprietà dell'oggetto i valori
		//null indica le prenotazioni che ovviamente quando creo una stanza sono null
		
		//Se la creazione della Stanza va a buon fine faccio una redirect direttamente sulla pagina
		//elenco.jsp che caricherà le nuove stanze compresa quella appena inserita.
		//In caso contrario manderò l'utente verso una pagina di errore.
		if(dd.create(s)) 
		{
			modelPO.addAttribute("elencoProjectOwners",dPO.leggiTutti());
			return "redirect:elencotaskp";
		}
		else
		{
			System.out.println("Errore nel salvataggio del Task.");
			return "erroresalvataggiotask.html";
		}
	}//Fine di nuovo
	
	@GetMapping("/dettagliTaskModifica")
	public String dettagliModifica(	@RequestParam("id") int id, Model model, Model modelListaPo)
	{
		List<ProjectOwner> listaPOs = dPO.leggiTutti();
		Task t = dd.cercaPerId(id);
		if(t == null)
		{
			
			return "redirect:elencotaskp";
		}
		else
		{
			modelListaPo.addAttribute("elencoProjectOwners", listaPOs);
			model.addAttribute("task", t);
			return "dettagliTaskModifica.jsp";
		}
	}

	@GetMapping("/modificatask")
	public String aggiorna( @RequestParam Map<String, String> parametri)
	{
		Task d = context.getBean(Task.class, parametri,null);
		//In console viene stampato il libro modificato per debug
		System.out.println(d.getId() + " " + d.getTitolo() + " " + d.getDifficolta()+" "+d.getIdProjectOwner());
		if(dd.update(d))
			return "redirect:elencotaskp";
		else
			return "Errore nell'aggiornamento del task";
	}
	
	@GetMapping("/cancellatask")
	public String elimina( @RequestParam("id") int idTask)
	{
		if(dd.delete(idTask))
			return "redirect:elencotaskp";
		else
			return "Errore nell'eliminazione del task con id " + idTask;	
	}
}

	
	


