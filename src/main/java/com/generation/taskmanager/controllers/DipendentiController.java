package com.generation.taskmanager.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.generation.taskmanager.dao.DAODipendente;
import com.generation.taskmanager.dao.DAOProjectOwner;
import com.generation.taskmanager.dao.DAOTask;
import com.generation.taskmanager.entities.Dipendente;
import com.generation.taskmanager.entities.ProjectOwner;

@Controller
public class DipendentiController 
{
	@Autowired
	private DAODipendente dd;
	
	@Autowired
	private DAOTask dt;
	
	@Autowired
	private DAOProjectOwner dPO;
	
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("/test")	
	@ResponseBody
	public String test()
	{
		String ris = "";
		for(Dipendente d : dd.leggiTutti())
			ris += d.toMap() + "<br>";
		return ris;
	}
	
	@GetMapping("dipendenti/elencodipendentid")	//localhost:8080/stanze/elenco
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
		return "elencodipendentid.jsp";
		//Spring non è nativamente in grado di compilare la JSP ma deve importare la DEPENDENCY
		//<dependency>
        //	<groupId>org.apache.tomcat.embed</groupId>
		//	<artifactId>tomcat-embed-jasper</artifactId>
		//	<scope>provided</scope>
		//</dependency>
	}
	@GetMapping("dipendenti/elencoprojectownerd")	//localhost:8080/stanze/elenco
	public String elenco(Model model)
	{
		/* addAttribute() è un metodo dell'oggetto Model di Spring che permette di inserire qualcosa
		 * nello scatolone etichettandolo:
		 * ds.leggiTutti() è ciò che noi mettiamo nel Model ossia la List<Stanza>
		 * "elencoStanze" è l'etichetta che potremo richiamare per accedere alla List<Stanza>
		 * dalla pagina JSP che sarà in grado di leggere il model.
		 * Quale sarà la pagina JSP che sarà in grado di leggere il model? "elenco.jsp"*/
		model.addAttribute("elencoProjectOwners", dPO.leggiTutti());
		//Se ci fate caso addAttribute() ricorda molto una mappa: chiave/valore
		//dove la chiave è l'etichetta e il valore è ciò che assegnamo alla chiave.
		
		//Il mapping non ritorna una pagina html ma una pagina JSP che andremo a creare
		//nella cartella webapp/stanze/elenco.jsp
		return "elencoprojectownerd.jsp";
		//Spring non è nativamente in grado di compilare la JSP ma deve importare la DEPENDENCY
		//<dependency>
        //	<groupId>org.apache.tomcat.embed</groupId>
		//	<artifactId>tomcat-embed-jasper</artifactId>
		//	<scope>provided</scope>
		//</dependency>
	}
	
	@GetMapping("dipendenti/dettaglid")
	public String dettaglid(@RequestParam("id") int id, Model model, Model modelListaTask)
	{
		//Cerchiamo la stanza all'interno del DB attraverso il metodo cercaPerNumero() di DAOStanze
		Dipendente cercato = dd.cercaPerId(id);
		
		//Se non troviamo nulla rimandiamo l'utente a una pagina apposita
		//Altrimenti inseriamo la stanza trovata all'interno del model che verrà spedito alla
		//pagina DETTAGLI.JSP
		if(cercato == null)
			return "dipendentenontrovato.html";
		else
		{
			modelListaTask.addAttribute("elencoTasks", dt.leggiTutti());
			model.addAttribute("dipendente", cercato);
			return "dettaglid.jsp";
		}
	}//Fine di dettagli
	
	@GetMapping("dipendenti/dettagli")
	public String dettagli(@RequestParam("id") int id, Model model, Model modelListaTask)
	{
		//Cerchiamo la stanza all'interno del DB attraverso il metodo cercaPerNumero() di DAOStanze
		Dipendente cercato = dd.cercaPerId(id);
		
		//Se non troviamo nulla rimandiamo l'utente a una pagina apposita
		//Altrimenti inseriamo la stanza trovata all'interno del model che verrà spedito alla
		//pagina DETTAGLI.JSP
		if(cercato == null)
			return "dipendentenontrovato.html";
		else
		{
			modelListaTask.addAttribute("elencoTasks", dt.leggiTutti());
			model.addAttribute("dipendente", cercato);
			return "dettagli.jsp";
		}
	}//Fine di dettagli
	
	@GetMapping("dipendenti/formnuovo")
	public String formnuovo(Model modelListaPo)
	{
		List<ProjectOwner> listaPOs = dPO.leggiTutti();
		modelListaPo.addAttribute("elencoProjectOwners", listaPOs);
		return "formnuovo.jsp";
	}
	
	@GetMapping("dipendenti/nuovo")
	public String nuovo(@RequestParam Map<String, String> parametri)
	{
		//Per poter creare la Stanza abbiamo bisogno del bean. Siccome siamo in un metodo 
		//dobbiamo fare riferimento a ApplicationContext
		
		Dipendente s = context.getBean(Dipendente.class, parametri, null);
		
		//Stanza.class indica la classe dell'oggetto che voglio creare
		//parametri sono la mappa che mando alla factory per assegnare alle proprietà dell'oggetto i valori
		//null indica le prenotazioni che ovviamente quando creo una stanza sono null
		
		//Se la creazione della Stanza va a buon fine faccio una redirect direttamente sulla pagina
		//elenco.jsp che caricherà le nuove stanze compresa quella appena inserita.
		//In caso contrario manderò l'utente verso una pagina di errore.
		if(dd.create(s)) {
			
			return "redirect:/projectowner/elencodipendentip";
		}
		else
		{
			System.out.println("Errore nel salvataggio del Dipendente.");
			return "erroresalvataggiodipendente.html";
		}
	}//Fine di nuovo
	
	@GetMapping("dipendenti/dettagliDipendentiModifica")
	public String dettagliModifica(	@RequestParam("id") int id, Model model,Model modelListaPo)
	{
		
		//Grazie all'id viene eseguita una ricerca per trovare il libro che si vuole modificare attraverso il metodo cercaPerId di DAOLibri
		List<ProjectOwner> listaPOs = dPO.leggiTutti();
		Dipendente d = dd.cercaPerId(id);
		if(d == null)
		{
			//Se non viene trovato il libro, l'utente viene riportato sulla pagina dell'elenco.
			return "redirect:projectowner/elencodipendentip";
		}
		else
		{
			//Una volta trovato il libro da modificare, viene inserito all'interno del model con l'attributo libro
			//Successivamente l'utente viene riportato alla pagina dettagliLibroModifica.jsp insieme al model contenente il libro da modificare.
			modelListaPo.addAttribute("elencoProjectOwners", listaPOs);
			model.addAttribute("dipendente", d);
			return "dettagliDipendentiModifica.jsp";
		}
	}
	
	@GetMapping("dipendenti/modificadipendente")
	public String aggiorna( @RequestParam Map<String, String> parametri)
	{
		Dipendente d = context.getBean(Dipendente.class, parametri,null);
		//In console viene stampato il libro modificato per debug
		System.out.println(d.getId() + " " + d.getNome() + " " + d.getSeniority()+" "+ d.getPassword()+" "+d.getIdProjectOwner());
		if(dd.update(d))
			return "redirect:/projectowner/elencodipendentip";
		else
			return "Errore nell'aggiornamento del dipendente";
	}
	
	@GetMapping("dipendenti/eliminaDipendente")
	public String elimina( @RequestParam("id") int idDipendente)
	{
		if(dd.delete(idDipendente))
			return "redirect:/projectowner/elencodipendentip";
		else
			return "Errore nell'eliminazione del dipendente con id " + idDipendente;	
	}

	
}
