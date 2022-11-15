<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.List" %>
 <%@ page import="com.generation.taskmanager.entities.*" %>
     
 <% Task t = (Task) request.getAttribute("task"); %>
 <% List<Dipendente> dipendenti = (List<Dipendente>) request.getAttribute("elencoDipendenti"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Assegnazione del task con id <%= t.getId() %></title>
			<style>
*{
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
}
.select{
background:#EAE6E1;
  border:0;
  font-weight:bold;
  padding:10px;
  border-radius:3px;
  width:100%;
  margin:5px 0;
  outline:medium none;
  color:#838383;

}

.select{
display:block;
  padding:10px;
  background:#50BFA4;
  border:0;
  border-radius:3px;
  font-weight:bold;
  width:100%;
  color:#fff;
  cursor:pointer;
  -webkit-transition:all 0.3s;
  -moz-transition:all 0.3s;
  transition:all 0.3s;

}

h1{
color: #3DA087;
}
body{
  background: url(https://i.imgur.com/MQB99sg.jpg) #a9b89e;
  font-family:Arial;
  font-size:12px;
  color:black;
}
input[type="hidden"]{
  background:#EAE6E1;
  border:0;
  font-weight:bold;
  padding:10px;
  border-radius:3px;
  width:100%;
  margin:5px 0;
  outline:medium none;
  color:#838383;
}
input[type="hidden"]{
  display:block;
  padding:10px;
  background:#50BFA4;
  border:0;
  border-radius:3px;
  font-weight:bold;
  width:100%;
  color:#fff;
  cursor:pointer;
  -webkit-transition:all 0.3s;
  -moz-transition:all 0.3s;
  transition:all 0.3s;
}
input[type="text"]{
  background:#EAE6E1;
  border:0;
  font-weight:bold;
  padding:10px;
  border-radius:3px;
  width:100%;
  margin:5px 0;
  outline:medium none;
  color:#838383;
}
input[type="text"]{
  display:block;
  padding:10px;
  background:#50BFA4;
  border:0;
  border-radius:3px;
  font-weight:bold;
  width:100%;
  color:#fff;
  cursor:pointer;
  -webkit-transition:all 0.3s;
  -moz-transition:all 0.3s;
  transition:all 0.3s;
}

input[type="date"]{
  display:block;
  padding:10px;
  background:#50BFA4;
  border:0;
  border-radius:3px;
  font-weight:bold;
  width:100%;
  color:#fff;
  cursor:pointer;
  -webkit-transition:all 0.3s;
  -moz-transition:all 0.3s;
  transition:all 0.3s;
}

input[type="date"]:hover{
  background:#58CCB0;
  }
.main-form{
  width:350px;
  margin: 100px auto;
  padding:50px;
  border: 1px solid rgba(0,0,0,0.1);
  -webkit-box-shadow:0 1px 2px rgba(0,0,0,0.2);
  background:#fff;
}

input[type="submit"]{
  display:block;
  padding:10px;
  background:#50BFA4;
  border:0;
  border-radius:3px;
  font-weight:bold;
  width:100%;
  color:#fff;
  cursor:pointer;
  -webkit-transition:all 0.3s;
  -moz-transition:all 0.3s;
  transition:all 0.3s;
}
input[type="submit"]:hover{
  background:#58CCB0;
  }
.main-form{
  width:350px;
  margin: 100px auto;
  padding:50px;
  border: 1px solid rgba(0,0,0,0.1);
  -webkit-box-shadow:0 1px 2px rgba(0,0,0,0.2);
  background:#fff;
}
.main-form > label{
  display:block;
  margin:10px 0 15px;
  line-height:15px;
  cursor:pointer;
}
.main-form > div{
  margin-top:20px;
}
a{
  color:#C4BCB0;
  text-decoration:none;
}
.main-form > a{
  font-size:11px;
  display:block;
  text-align:center;
  margin:10px 0;
}
.main-form > div >a:first-child{
  font-weight:bold;
}
.main-form > div >a:nth-child(2){
  border:1px solid #3DA087;
  display:inline-block;
  border-radius:3px;
  color:#3DA087;
  font-weight:bold;
  padding:7px 15px;
  margin-left:28px;
  -webkit-transition:all 0.3s;
  -moz-transition:all 0.3s;
  transition:all 0.3s;
}
.main-form > div >a:nth-child(2):hover{
  background:#3DA087;
  color:#fff;
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
		<form action="/tasksassegnati/assegna" method="get">
		<div class="main-form">
		<h1 align="center">ASSEGNA TASK</h1>
			SCADENZA <input type="date" name="scadenza">
			<br>
			STATO 	<select class="select" name="stato">
						<option value="Programmato">Programmato</option>
						<option value="In corso">In corso</option>
						<option value="Completato">Completato</option>
					</select>
			<br>
			IDDIPENDENTE  	<select class="select" name="iddipendente">
								<% for(Dipendente d : dipendenti) {%>
									<option value="<%= d.getId() %>"> <%= d.getNome()%></option> 
								<%} %>
							</select>
			<br>				
			TITOLO TASK  <input type="text" name="titolo" value="<%= t.getTitolo() %>" readonly>
			<br>
			<input type="hidden" name="idtask" value="<%= t.getId() %>">	
			<br>
			<input type="submit" value="ASSEGNA">
		</div>
		</form>
       <a href="/tasks/elencotaskp"> <button class="btn" role="button">ANNULLA</button></a>
	</body>
</html>