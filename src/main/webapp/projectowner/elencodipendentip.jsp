<!--	Questa intestazione serve solo a specificare il tipo di codice che troviamo all'interno della
		pagina ossia JAVA. Una pagina JSP � una pagina HTML con del codice JAVA.
		Rispetto alle pagine classiche potrebbe risultare pi� scomoda perch� quando cambio qualcosa
		al suo interno devo riavviare l'intera app.
		Ci� che la rende invece migliore � la sicurezza perch� le operazioni al suo interno non
		saranno scritte su file JS e quindi non saranno visibili ispezionando la pagina.
		L'intera compilazione avviene lato backend e a livello di frontend non si vede nulla.
		I tag che troviamo sono diversi da quelli soliti:
		< %@ -> Questi servono per configurare la pagina
		< %	-> Questi servono quando devo scrivere codice JAVA
		< %= -> Questi servono per stampare
 -->
<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!--	Per prima cosa imprtiamo List e Stanza per rendere la JSP
		in grado di gestire quel tipo di oggetto.
 -->
 <%@ page import="java.util.List" %>
 <%@ page import="com.generation.taskmanager.entities.Dipendente" %>
 <!--	Ora che abbiamo importato le classi base, andiamo a leggere il Model ossia lo scatolone
 		nel quale ci sono gli oggetti con cui dovremo lavorare.
 		request.getAttribute("elencoStanze") restituisce il contenuto del Model etichettato come
 		"elencoStanze" cio� List<Stanza>. Siccome per� � un oggetto generico di Spring,
 		per poter essere associato a List<Stanza> stanze dobbiamo castarlo anche se JSP ci mette
 		un warning perch� il casting cos� non � sicuro.
 -->
 <% List<Dipendente> dipendenti = (List<Dipendente>) request.getAttribute("elencoDipendenti"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Elenco dei dipendenti</title>
		<style>
		.styled-table {
          border-collapse: collapse;
          margin: 25px 0;
          font-size: 0.9em;
          margin-left: auto;
          margin-right: auto;
          font-family: sans-serif;
          min-width: 400px;
          box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
          }
          .styled-table thead tr {
           background-color: #009879;
           color: #ffffff;
           text-align: left;
            }
		.styled-table th,
        .styled-table td {
           padding: 12px 15px;
            }
            .styled-table tbody tr {
            border-bottom: 1px solid #dddddd;
            }

      .styled-table tbody tr:nth-of-type(even) {
         background-color: white;
        }
        .styled-table tbody tr:nth-of-type(odd) {
         background-color: white;
        }
         .styled-table tbody tr:last-of-type {
          border-bottom: 2px solid #009879;
          }
          .styled-table tbody tr.active-row {
          font-weight: bold;
          color: #009879;
          }
          nav {
	margin: 27px auto 0;

	position: relative;
	width: 590px;
	height: 50px;
	background-color: #34495e;
	border-radius: 8px;
	font-size: 0;
}
nav a {
	line-height: 50px;
	height: 100%;
	font-size: 15px;
	display: inline-block;
	position: relative;
	z-index: 1;
	text-decoration: none;
	text-transform: uppercase;
	text-align: center;
	color: white;
	cursor: pointer;
}
nav .animation {
	position: absolute;
	height: 100%;
	top: 0;
	z-index: 0;
	transition: all .5s ease 0s;
	border-radius: 8px;
}
a:nth-child(1) {
	width: 100px;
}
a:nth-child(2) {
	width: 110px;
}
a:nth-child(3) {
	width: 100px;
}
a:nth-child(4) {
	width: 160px;
}
a:nth-child(5) {
	width: 120px;
}
nav .start-home, a:nth-child(1):hover~.animation {
	width: 100px;
	left: 0;
	background-color: #1abc9c;
}
nav .start-about, a:nth-child(2):hover~.animation {
	width: 110px;
	left: 100px;
	background-color: #e74c3c;
}
nav .start-blog, a:nth-child(3):hover~.animation {
	width: 100px;
	left: 210px;
	background-color: #3498db;
}
nav .start-portefolio, a:nth-child(4):hover~.animation {
	width: 160px;
	left: 310px;
	background-color: #9b59b6;
}
nav .start-contact, a:nth-child(5):hover~.animation {
	width: 120px;
	left: 470px;
	background-color: #e67e22;
}
*{
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
}
body{
  background: url(https://i.imgur.com/MQB99sg.jpg) #a9b89e;
  font-family:Arial;
  font-size:12px;
  color:black;
}
h1 {
	text-align: center;
	margin: 40px 0 40px;
	text-align: center;
	font-size: 30px;
	color: #ecf0f1;
	text-shadow: 2px 2px 4px #000000;
	font-family: Verdana, sans-serif;
}

p {
    text-align: center;
	text-align: center;
	font-size: 20px;
	color: #ecf0f1;
	text-shadow: 2px 2px 4px #000000;
	font-family: Verdana, sans-serif;
}

span {
    color: #2BD6B4;
}

.button {
  background-color: #50BFA4;
  border: 1px solid transparent;
  border-radius: 3px;
  box-shadow: rgba(255, 255, 255, .4) 0 1px 0 0 inset;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  font-family: -apple-system,system-ui,"Segoe UI","Liberation Sans",sans-serif;
  font-size: 13px;
  font-weight: 400;
  line-height: 1.15385;
  margin: 0;
  outline: none;
  padding: 8px .8em;
  position: relative;
  text-align: center;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  vertical-align: baseline;
  white-space: nowrap;
}

.button:hover,
.button:focus {
  background-color: #07c;
}

.button:focus {
  box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
}

.button:active {
  background-color: #0064bd;
  box-shadow: none;
}
.btn {
  background-color: #50BFA4;
  border: 1px solid transparent;
  border-radius: 3px;
  box-shadow: rgba(255, 255, 255, .4) 0 1px 0 0 inset;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  display: block;
  font-family: -apple-system,system-ui,"Segoe UI","Liberation Sans",sans-serif;
  font-size: 13px;
  font-weight: 400;
  line-height: 1.15385;
  margin: 0 auto;
  outline: none;
  padding: 8px .8em;
  text-align: center;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  vertical-align: baseline;
  white-space: nowrap;
}

.btn:hover,
.btn:focus {
  background-color: #07c;
}

.btn:focus {
  box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
}

.btn:active {
  background-color: #0064bd;
  box-shadow: none;
}
		</style>
	</head>
	<body>
		<!--	Inseriamo il file menu.html all'inizio della pagina 
				Rispetto a JS non � necessario specificare il percorso del file perch� anche se elenco.jsp
				� all'interno della cartella stanze, per JAVA un file da leggere partir� sempre
				dalla cartella WEBAPP (segue il pattern MVC) quindi scrivere /menu.html
				� come dirgli webapp/menu.html
				Se menu.html fosse all'interno della cartella stanze dovrei scrivere /stanze/menu.html
				perch� altrimenti lo cercherebbe nella cartella principale delle viste ossia webapp.
		-->
		<%@ include file="/menup.html" %>
		
		<h1>ELENCO DEI DIPENDENTI</h1>
		<% if(dipendenti.size() == 0){ %>
		 <h1 align="center">NESSUNO</h1>
		<%} %>
		
		<table class="styled-table">
		<thead>
			<tr>
				<th>ID</th>
				<th>NOME</th>
				<th>SENIORITY</th>
				<th>IDPO</th>
				<th></th>
				<th></th>
				<th></th>
				
				<!--	Aggiungiamo un link per la creazione di una nuova stanza. Il link rimander� direttamente
						al mapping "formnuovo" su StanzeController. -->
			</tr>
		</thead>
			
			<!--	Ora che abbiamo la nostra intestazione dobbiamo ciclare le stanze per stamparle
					< %= questo tag indica STAMPA -->
		<tbody>
			 <% for(Dipendente d : dipendenti) {%>
			 	<tr>
			 		<td><%= d.getId() %></td>
			 		<td><%= d.getNome() %></td>
			 		<td><%= d.getSeniority() %></td>
			 		<td><%= d.getIdProjectOwner()%></td>
			 		<td>
			 			<a href="/dipendenti/dettagli?id=<%= d.getId() %>"><button class="button" role="button">Dettagli</button></a>
 					<td>
			 			<a href="/dipendenti/dettagliDipendentiModifica?id=<%= d.getId() %>"><button class="button" role="button">Modifica</button></a>
			 		</td>
			 		<td>
			 			<a href="/dipendenti/eliminaDipendente?id=<%= d.getId() %>"><button class="button" role="button">Elimina</button></a>
			 		</td>	 
			 	</tr>
			<%} %>
		</tbody>
		</table><br>			
			<a href="/dipendenti/formnuovo"><button class="btn" role="button">NUOVO DIPENDENTE</button></a>			
	</body>
</html>