package com.generation.taskmanager.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generation.taskmanager.dao.DAOTask;
import com.generation.taskmanager.dao.DAOTasksAssegnati;
import com.generation.taskmanager.entities.Task;
import com.generation.taskmanager.entities.TaskAssegnato;
import com.google.gson.Gson;

@Controller
@RequestMapping("/tasksassegnati")
public class TaskAssegnatoController {
	@Autowired
	private DAOTasksAssegnati dt;
	
	@Autowired
	private DAOTask ds;

	
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("/test")	
	@ResponseBody
	public String test()
	{
		String ris = "";
		for(TaskAssegnato d : dt.leggiTutti())
			ris += d.toMap() + "<br>";
		return ris;
	}
	
	@GetMapping("/json")
	@ResponseBody
	public String stampaTaskAssegnato()
	{
		Gson gson = new Gson();
		return gson.toJson(dt.leggiTutti());
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
		model.addAttribute("elencoTasksAssegnati", dt.leggiTutti());
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
		model.addAttribute("elencoTasksAssegnati", dt.leggiTutti());
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
	
	
	/*@GetMapping("/formassegnatask")
	public String formnassegna()
	{
		System.out.println("dentro form assegna task");
		return "formassegnatask.jsp";
	}*/
	
	
	
	@GetMapping("/assegna")
	public String nuovo(@RequestParam Map<String, String> parametri)
	{
		//Per poter creare la Stanza abbiamo bisogno del bean. Siccome siamo in un metodo 
		//dobbiamo fare riferimento a ApplicationContext
		TaskAssegnato s = context.getBean(TaskAssegnato.class, parametri);
		
		//Stanza.class indica la classe dell'oggetto che voglio creare
		//parametri sono la mappa che mando alla factory per assegnare alle proprietà dell'oggetto i valori
		//null indica le prenotazioni che ovviamente quando creo una stanza sono null
		
		//Se la creazione della Stanza va a buon fine faccio una redirect direttamente sulla pagina
		//elenco.jsp che caricherà le nuove stanze compresa quella appena inserita.
		//In caso contrario manderò l'utente verso una pagina di errore.
		if(dt.create(s))
			return "redirect:../dipendenti/dettagli?id=" +s.getIdDipendente();
		else
		{
			System.out.println("Errore nel salvataggio del Task.");
			return "erroresalvataggiotask.html";
		}
	}//Fine di nuovo
	
	@GetMapping("/dettagliTasksAssegnatiModifica")
	public String dettagliModifica(	@RequestParam("id") int id, Model model,Model modelTask)
	{
		Task t =  ds.cercaPerId(dt.cercaPerId(id).getIdTask());
		TaskAssegnato ta = dt.cercaPerId(id);
		if(t == null)
		{
			
			return "redirect:elencotaskp";
		}
		else
		{
			modelTask.addAttribute("task", t);
			model.addAttribute("taskassegnato", ta);
			return "dettagliTasksAssegnatiModifica.jsp";
		}
	}
	
	@GetMapping("/dettagliTasksAssegnatiModificad")
	public String dettagliModificad(@RequestParam("id") int id, Model model,Model modelTask)
	{
		Task t =  ds.cercaPerId(dt.cercaPerId(id).getIdTask());
		TaskAssegnato ta = dt.cercaPerId(id);
		if(t == null)
		{
			
			return "redirect:elencotaskp";
		}
		else
		{
			modelTask.addAttribute("task", t);
			model.addAttribute("taskassegnato", ta);
			return "dettagliTasksAssegnatiModificad.jsp";
		}
	}

	@GetMapping("/modificatasksassegnati")
	public String aggiorna( @RequestParam Map<String, String> parametri)
	{
		TaskAssegnato d = context.getBean(TaskAssegnato.class, parametri);
		//In console viene stampato il libro modificato per debug
		System.out.println(d.getId() + " " + d.getScadenza() + " " + d.getStato()+" "+d.getIdDipendente() + " " + d.getIdTask());
		if(dt.update(d))
			return "redirect:../dipendenti/dettagli?id="+d.getIdDipendente();
		else
			return "Errore nell'aggiornamento del task";
	}
	
	@GetMapping("/modificatasksassegnatid")
	public String aggiornad( @RequestParam Map<String, String> parametri)
	{
		TaskAssegnato d = context.getBean(TaskAssegnato.class, parametri);
		//In console viene stampato il libro modificato per debug
		System.out.println(d.getId() + " " + d.getScadenza() + " " + d.getStato()+" "+d.getIdDipendente() + " " + d.getIdTask());
		if(dt.update(d))
			return "redirect:../dipendenti/dettaglid?id="+d.getIdDipendente();
		else
			return "Errore nell'aggiornamento del task";
	}
	@GetMapping("/cancellatask")
	public String elimina( @RequestParam("id") int idTaskAssegnato)
	{
		String x = dt.cercaPerId(idTaskAssegnato).getIdDipendente();
		if(dt.delete(idTaskAssegnato))
			return "redirect:../dipendenti/dettagli?id="+x;
		else
			return "Errore nell'eliminazione del task con id " + idTaskAssegnato;	
	}

}
