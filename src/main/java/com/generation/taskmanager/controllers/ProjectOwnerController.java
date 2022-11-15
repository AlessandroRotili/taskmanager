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
import com.generation.taskmanager.dao.DAOTasksAssegnati;
import com.generation.taskmanager.entities.ProjectOwner;
import com.generation.taskmanager.entities.Task;
import com.generation.taskmanager.entities.TaskAssegnato;

@Controller
@RequestMapping("/projectowner")
public class ProjectOwnerController {
	@Autowired
	private DAOProjectOwner dp;
	
	@Autowired
	private DAODipendente dd;
	
	@Autowired
	private DAOTasksAssegnati dta;
	
	@Autowired
	private DAOTask dt;
	
	
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("/test")	
	@ResponseBody
	public String test()
	{
		String ris = "";
		for(ProjectOwner d : dp.leggiTutti())
			ris += d.toMap() + "<br>";
		return ris;
	}
	
	@GetMapping("/elencodipendentip")	//localhost:8080/stanze/elenco
	public String elencod(Model model)
	{
		/* addAttribute() è un metodo dell'oggetto Model di Spring che permette di inserire qualcosa
		 * nello scatolone etichettandolo:
		 * ds.leggiTutti() è ciò che noi mettiamo nel Model ossia la List<Stanza>
		 * "elencoStanze" è l'etichetta che potremo richiamare per accedere alla List<Stanza>
		 * dalla pagina JSP che sarà in grado di leggere il model.
		 * Quale sarà la pagina JSP che sarà in grado di leggere il model? "elenco.jsp"*/
		model.addAttribute("elencoDipendenti", dd.leggiTutti());
		//Se ci fate caso addAttribute() ricorda molto una mappa: chiave/valore
		//dove la chiave è l'etichetta e il valore è ciò che assegnamo alla chiave.
		
		//Il mapping non ritorna una pagina html ma una pagina JSP che andremo a creare
		//nella cartella webapp/stanze/elenco.jsp
		return "elencodipendentip.jsp";
		//Spring non è nativamente in grado di compilare la JSP ma deve importare la DEPENDENCY
		//<dependency>
        //	<groupId>org.apache.tomcat.embed</groupId>
		//	<artifactId>tomcat-embed-jasper</artifactId>
		//	<scope>provided</scope>
		//</dependency>
	}
	@GetMapping("/elencoprojectownerp")	//localhost:8080/stanze/elenco
	public String elencop(Model model)
	{
		/* addAttribute() è un metodo dell'oggetto Model di Spring che permette di inserire qualcosa
		 * nello scatolone etichettandolo:
		 * ds.leggiTutti() è ciò che noi mettiamo nel Model ossia la List<Stanza>
		 * "elencoStanze" è l'etichetta che potremo richiamare per accedere alla List<Stanza>
		 * dalla pagina JSP che sarà in grado di leggere il model.
		 * Quale sarà la pagina JSP che sarà in grado di leggere il model? "elenco.jsp"*/
		model.addAttribute("elencoProjectOwners", dp.leggiTutti());
		//Se ci fate caso addAttribute() ricorda molto una mappa: chiave/valore
		//dove la chiave è l'etichetta e il valore è ciò che assegnamo alla chiave.
		
		//Il mapping non ritorna una pagina html ma una pagina JSP che andremo a creare
		//nella cartella webapp/stanze/elenco.jsp
		return "elencoprojectownerp.jsp";
		//Spring non è nativamente in grado di compilare la JSP ma deve importare la DEPENDENCY
		//<dependency>
        //	<groupId>org.apache.tomcat.embed</groupId>
		//	<artifactId>tomcat-embed-jasper</artifactId>
		//	<scope>provided</scope>
		//</dependency>
	}
	
	@GetMapping("/dettagli")
	public String dettagli(@RequestParam("id") int id, Model model, Model listaTasks, Model listaTask)
	{
		
		//Cerchiamo la stanza all'interno del DB attraverso il metodo cercaPerNumero() di DAOStanze
		List<TaskAssegnato> ta = dta.leggiTutti();
		ProjectOwner cercato = dp.cercaPerId(id);
		List<Task> t = dt.leggiTutti();
		
		//Se non troviamo nulla rimandiamo l'utente a una pagina apposita
		//Altrimenti inseriamo la stanza trovata all'interno del model che verrà spedito alla
		//pagina DETTAGLI.JSP
		if(cercato == null)
			return "ponontrovato.html";
		else
		{ 
			listaTasks.addAttribute("elencoTasksAssegnati",ta);
			model.addAttribute("projectowner", cercato);
			listaTask.addAttribute("elencoTask", t);
			return "dettagli.jsp";
		}
	}//Fine di dettagli
	
	
	@GetMapping("/dettaglip")
	public String dettaglip(@RequestParam("id") int id, Model model, Model listaTasks, Model listaTask)
	{
		
		//Cerchiamo la stanza all'interno del DB attraverso il metodo cercaPerNumero() di DAOStanze
		List<TaskAssegnato> ta = dta.leggiTutti();
		ProjectOwner cercato = dp.cercaPerId(id);
		List<Task> t = dt.leggiTutti();
		
		//Se non troviamo nulla rimandiamo l'utente a una pagina apposita
		//Altrimenti inseriamo la stanza trovata all'interno del model che verrà spedito alla
		//pagina DETTAGLI.JSP
		if(cercato == null)
			return "ponontrovato.html";
		else
		{
			listaTask.addAttribute("elencoTask", t);
			listaTasks.addAttribute("elencoTasksAssegnati",ta);
			model.addAttribute("projectowner", cercato);
			return "dettaglip.jsp";
		}
	}//Fine di dettagli
	
	@GetMapping("/formnuovo")
	public String formnuovo()
	{
		return "formnuovo.html";
	}
	
	@GetMapping("/nuovo")
	public String nuovo(@RequestParam Map<String, String> parametri)
	{
		//Per poter creare la Stanza abbiamo bisogno del bean. Siccome siamo in un metodo 
		//dobbiamo fare riferimento a ApplicationContext
		ProjectOwner s = context.getBean(ProjectOwner.class, parametri,null,null);
		//Stanza.class indica la classe dell'oggetto che voglio creare
		//parametri sono la mappa che mando alla factory per assegnare alle proprietà dell'oggetto i valori
		//null indica le prenotazioni che ovviamente quando creo una stanza sono null
		
		//Se la creazione della Stanza va a buon fine faccio una redirect direttamente sulla pagina
		//elenco.jsp che caricherà le nuove stanze compresa quella appena inserita.
		//In caso contrario manderò l'utente verso una pagina di errore.
		if(dp.create(s))
			return "redirect:elencoprojectownerp";
		else
		{
			System.out.println("Errore nel salvataggio del PO.");
			return "erroresalvataggiopo.html";
		}
	}//Fine di nuovo
	
	@GetMapping("/modificaprojectowner")
	public String aggiorna( @RequestParam Map<String, String> parametri)
	{
		ProjectOwner d = context.getBean(ProjectOwner.class, parametri);
		//In console viene stampato il libro modificato per debug
		System.out.println(d.getId() + " " + d.getNome() + " " + d.getPassword());
		if(dp.update(d))
			return "redirect:elencoprojectownerp";
		else
			return "Errore nell'aggiornamento del projectowner";
	}
	
	@GetMapping("/eliminaprojectowner")
	public String cancella( @RequestParam("id") int idProjectOwner)
	{		
		if(dp.delete(idProjectOwner))
			return "redirect:elencoprojectownerp";
		else
			return "Errore nell'aggiornamento del projectowner";
	}
	
}
	


