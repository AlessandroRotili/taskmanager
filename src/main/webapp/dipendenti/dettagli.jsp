<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!--	Importiamo le entities -->
<%@ page import="com.generation.taskmanager.entities.*" %>
<%@ page import="java.util.List" %>
<!-- 	Andiamo a leggere il model che ci arriva dall'URL e lo trasformiamo in un oggetto di tipo
		Stanza-->
<% Dipendente d = (Dipendente) request.getAttribute("dipendente"); %>
<% List<Task> tasks = (List<Task>) request.getAttribute("elencoTasks"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Dettagli del dipendente con id <%= d.getId() %></title>
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
a{
    text-align: center;
	text-decoration: none;
	text-align: center;
	color: white;
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
		<h1>Dettagli di  <%= d.getNome() %></h1>
		
		<p>NOME: <%= d.getNome() %></p><br>
		<p>SENIORITY: <%= d.getSeniority() %></p><br>
		
		<!--	Dopo aver stampato i dettagli di base, andiamo a stampare le prenotazioni
				ciclando l'intero vettore di Prenotazione che appartiene ad ogni stanza -->
		<h1>ELENCO DEI TASKS ASSEGNATI</h1><br>
		<% if(d.getTaskAssegnati().size() == 0){ %>
		    <h1 align="center">NESSUNO</h1>
		<%} %>
		
		<!-- Se ci sono prenotazioni le cicliamo e le stampiamo sul web come Unordered List -> UL -->
		<!-- Le liste stampabili di default sul web sono UL oppure OL
				UL -> unordered list -> elenco puntato (. . . . . . ...)
				OL -> ordered list -> elenco numerato (1,2,3,4,5,...)
		-->
		<table class="styled-table">
		  <thead>
			<tr>
				<th>TITOLO TASK</th>
				<th>SCADENZA</th>
				<th>STATO</th>
				
				<!--	Aggiungiamo un link per la creazione di una nuova stanza. Il link rimanderà direttamente
						al mapping "formnuovo" su StanzeController. -->
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<% for(TaskAssegnato ta : d.getTaskAssegnati())
			{ %>
				<% String titolo = ""; %>
				<% for(Task t : tasks)
				{ %>
					<% if(ta.getIdTask() == t.getId()) 
					{%>
						<% titolo = t.getTitolo(); 
					}%>
				<%} %>		
				
				<tr>
					<td><%= titolo %></td>
				 	<td><%= ta.getScadenza() %></td>
				 	<td><%= ta.getStato() %></td>
				 	<td>
				 		<a href="/tasksassegnati/dettagliTasksAssegnatiModifica?id=<%= ta.getId() %>"> <button class="button" role="button">Modifica</button></a>
				 	</td>
				 	<td>
				 		<a href="/tasksassegnati/cancellatask?id=<%= ta.getId() %>"> <button class="button" role="button">Elimina</button></a>
				 	</td>
				</tr>
				
			<%} %>
			</tbody>
		</table>
		
		<a href="/homep"> <button class="btn" role="button">HOME</button></a>
	</body>
</html>