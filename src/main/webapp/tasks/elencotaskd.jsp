
<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!--	Per prima cosa imprtiamo List e Stanza per rendere la JSP
		in grado di gestire quel tipo di oggetto.
 -->
 <%@ page import="java.util.List" %>
 <%@ page import="com.generation.taskmanager.entities.Task" %>
 <!--	Ora che abbiamo importato le classi base, andiamo a leggere il Model ossia lo scatolone
 		nel quale ci sono gli oggetti con cui dovremo lavorare.
 		request.getAttribute("elencoStanze") restituisce il contenuto del Model etichettato come
 		"elencoStanze" cio� List<Stanza>. Siccome per� � un oggetto generico di Spring,
 		per poter essere associato a List<Stanza> stanze dobbiamo castarlo anche se JSP ci mette
 		un warning perch� il casting cos� non � sicuro.
 -->
 <% List<Task> tasks = (List<Task>) request.getAttribute("elencoTasks"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Elenco dei tasks</title>
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
		</style>
	</head>
	<body>
		
		<%@ include file="/menud.html" %>		
		
		<h1>ELENCO DEI TASKS</h1>
         <% if(tasks.size() == 0){ %>
		 <h1 align="center">NESSUNO</h1>
		<%} %>
		
		<table class="styled-table">
		  <thead>
			<tr>
				<th>ID</th>
				<th>TITOLO</th>
				<th>DIFFICOLTA</th>
				<th>IDPO</th>
				
				<!--	Aggiungiamo un link per la creazione di una nuova stanza. Il link rimander� direttamente
						al mapping "formnuovo" su StanzeController. -->
			</tr>
		  </thead>
		
			
			<tbody>
			 <% for(Task t : tasks) {%>
			 	<tr>
			 		<td><%= t.getId() %></td>
			 		<td><%= t.getTitolo() %></td>
			 		<td><%= t.getDifficolta() %></td>
			 		<td><%= t.getIdProjectOwner() %></td>
			 	</tr>
			 	<%} %>
			</tbody>
		</table>
		<br>
	</body>
</html>