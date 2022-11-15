package com.generation.taskmanager.controllers;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.generation.taskmanager.dao.DAODipendente;
import com.generation.taskmanager.dao.DAOProjectOwner;

@Controller
public class HomeController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	@Autowired
	private DAODipendente dd;

	@Autowired
	private DAOProjectOwner dp;

	//http://localhost:8080/
	@GetMapping("/")
	public String home(HttpSession session)
	{
		//Prima di rimandarlo alla home controlliamo la session e verifichiamo il valore dell'attributo
		//login. Se è null significa che non è loggato e quindi lo rimandiamo al mapping formlogin.
		//Se è loggato la riga 46 non viene eseguita e l'utente vede direttamente home.html
		if(session.getAttribute("login") == null)
			return "redirect:/formlogin";
		else if(session.getAttribute("ruolo") == "dipendente")
			return "homed.html";
		else if (session.getAttribute("ruolo") == "projectowner")
			return "homep.html";
		else return "error.html";

	}

	//http://localhost:8080/
	@GetMapping("/homed")
	public String homed(HttpSession session)
	{
		if(session.getAttribute("login") == null)
			return "redirect:/formlogin";

		return "homed.html";
	}

	//http://localhost:8080/
	@GetMapping("/homep")
	public String homep(HttpSession session)
	{
		if(session.getAttribute("login") == null)
			return "redirect:/formlogin";

		return "homep.html";
	}

	@GetMapping("/formlogin")
	public String formlogin()
	{
		return "formlogin.html";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request)
	{
		String nome = request.getParameter("username");
		String password = request.getParameter("password");
		//Il metodo riceve due parametri dalla URL e ne prende un terzo:
		//stringUser è la variabile nella quale inseriamo il valore dell'input "username" della formlogin.html
		//stringPass è la variabile nella quale inseriamo il valore dell'input "password" della formlogin.html
		//session è l'intera SESSION dell'utente -> una session è la carta d'identità dell'utente sul sito
		if(dd.login(nome,password))
		{
			//Se username e password sono corretti, imposto l'attributo login della session su "ok"
			//e mi porto dietro anche il valore di username
			request.getSession().setAttribute("login", "ok");
			request.getSession().setAttribute("ruolo", "dipendente");
			//Volendo qui potete caricare, dopo aver fatto il metodo in DAOUtenti ad esempio,
			//l'intero oggetto Utente così da richiamarlo in qualsiasi parte del programma.

			//Se tutto va a buon fine faccio una redirect sul mapping("/") ossia sulla pagina home
			//ma stavolta l'utente avrà le caratteristiche corrette sulla session per poter essere loggato
			return "redirect:/homed";
		}
		else if(dp.login(nome,password)) {
			//Se username e password sono corretti, imposto l'attributo login della session su "ok"
			//e mi porto dietro anche il valore di username
			request.getSession().setAttribute("login", "ok");
			request.getSession().setAttribute("ruolo", "projectowner");
			//Volendo qui potete caricare, dopo aver fatto il metodo in DAOUtenti ad esempio,
			//l'intero oggetto Utente così da richiamarlo in qualsiasi parte del programma.

			//Se tutto va a buon fine faccio una redirect sul mapping("/") ossia sulla pagina home
			//ma stavolta l'utente avrà le caratteristiche corrette sulla session per poter essere loggato
			return "redirect:/homep";
		}
		else if(nome.equals("utente") && password.equals("password")){
			
			request.getSession().setAttribute("login", "ok");
			request.getSession().setAttribute("ruolo", "projectowner");
			return "redirect:/homep";
		}
		else
		{
			//Se i dati inseriti non sono corretti, lo rimando nuovamente al mapping formlogin
			//cioè alla form di login.
			return "redirect:/formlogin";
		}
	}//Fine di login

	//Creiamo un mapping per riportare la session con i valori null.
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.setAttribute("login", null);
		session.setAttribute("user", null);
		return "redirect:/formlogin";
	}
}
