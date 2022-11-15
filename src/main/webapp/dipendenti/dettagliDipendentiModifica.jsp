<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!-- Importiamo tutte le entities con *-->
<%@ page import="com.generation.taskmanager.entities.*" %> <%@ page
import="java.util.List" %>
<!-- 
	Creaimo per prima cosa l'oggetto Libro leggendo dal Model
	che � stato riempito grazie al mapping /dettagli in HomeController.
	Se siamo arrivati fino a qui significa che il libro cercato esiste, altrimenti il Model sarebbe vuoto.	
-->
<% Dipendente d = (Dipendente) request.getAttribute("dipendente"); %> <%
List<ProjectOwner>
  POs = (List<ProjectOwner
    >) request.getAttribute("elencoProjectOwners"); %>
    <!DOCTYPE html>
    <html>
      <head>
        <meta charset="ISO-8859-1" />
        <title>Dettagli</title>
        <style>
          * {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
          }
          .select {
            background: #eae6e1;
            border: 0;
            font-weight: bold;
            padding: 10px;
            border-radius: 3px;
            width: 100%;
            margin: 5px 0;
            outline: medium none;
            color: #838383;
          }

          .select {
            display: block;
            padding: 10px;
            background: #50bfa4;
            border: 0;
            border-radius: 3px;
            font-weight: bold;
            width: 100%;
            color: #fff;
            cursor: pointer;
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            transition: all 0.3s;
          }

          h1 {
            color: #3da087;
          }
          body {
            background: url(https://i.imgur.com/MQB99sg.jpg) #a9b89e;
            font-family: Arial;
            font-size: 12px;
            color: black;
          }
          input[type="hidden"] {
            background: #eae6e1;
            border: 0;
            font-weight: bold;
            padding: 10px;
            border-radius: 3px;
            width: 100%;
            margin: 5px 0;
            outline: medium none;
            color: #838383;
          }
          input[type="hidden"] {
            display: block;
            padding: 10px;
            background: #50bfa4;
            border: 0;
            border-radius: 3px;
            font-weight: bold;
            width: 100%;
            color: #fff;
            cursor: pointer;
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            transition: all 0.3s;
          }
          input[type="text"] {
            background: #eae6e1;
            border: 0;
            font-weight: bold;
            padding: 10px;
            border-radius: 3px;
            width: 100%;
            margin: 5px 0;
            outline: medium none;
            color: #838383;
          }
          input[type="text"] {
            display: block;
            padding: 10px;
            background: #50bfa4;
            border: 0;
            border-radius: 3px;
            font-weight: bold;
            width: 100%;
            color: #fff;
            cursor: pointer;
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            transition: all 0.3s;
          }

          input[type="date"] {
            display: block;
            padding: 10px;
            background: #50bfa4;
            border: 0;
            border-radius: 3px;
            font-weight: bold;
            width: 100%;
            color: #fff;
            cursor: pointer;
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            transition: all 0.3s;
          }

          input[type="date"]:hover {
            background: #58ccb0;
          }
          .main-form {
            width: 350px;
            margin: 100px auto;
            padding: 50px;
            border: 1px solid rgba(0, 0, 0, 0.1);
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
            background: #fff;
          }

          input[type="submit"] {
            display: block;
            padding: 10px;
            background: #50bfa4;
            border: 0;
            border-radius: 3px;
            font-weight: bold;
            width: 100%;
            color: #fff;
            cursor: pointer;
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            transition: all 0.3s;
          }
          input[type="submit"]:hover {
            background: #58ccb0;
          }
          .main-form {
            width: 350px;
            margin: 100px auto;
            padding: 50px;
            border: 1px solid rgba(0, 0, 0, 0.1);
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
            background: #fff;
          }
          .main-form > label {
            display: block;
            margin: 10px 0 15px;
            line-height: 15px;
            cursor: pointer;
          }
          .main-form > div {
            margin-top: 20px;
          }
          a {
            color: #c4bcb0;
            text-decoration: none;
          }
          .main-form > a {
            font-size: 11px;
            display: block;
            text-align: center;
            margin: 10px 0;
          }
          .main-form > div > a:first-child {
            font-weight: bold;
          }
          .main-form > div > a:nth-child(2) {
            border: 1px solid #3da087;
            display: inline-block;
            border-radius: 3px;
            color: #3da087;
            font-weight: bold;
            padding: 7px 15px;
            margin-left: 28px;
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            transition: all 0.3s;
          }
          .main-form > div > a:nth-child(2):hover {
            background: #3da087;
            color: #fff;
          }
          .button {
            background-color: #50bfa4;
            border: 1px solid transparent;
            border-radius: 3px;
            box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0 0 inset;
            box-sizing: border-box;
            color: #fff;
            cursor: pointer;
            display: inline-block;
            font-family: -apple-system, system-ui, "Segoe UI", "Liberation Sans",
              sans-serif;
            font-size: 13px;
            font-weight: 400;
            line-height: 1.15385;
            margin: 0;
            outline: none;
            padding: 8px 0.8em;
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
            box-shadow: 0 0 0 4px rgba(0, 149, 255, 0.15);
          }

          .button:active {
            background-color: #0064bd;
            box-shadow: none;
          }

          .btn {
            background-color: #50bfa4;
            border: 1px solid transparent;
            border-radius: 3px;
            box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0 0 inset;
            box-sizing: border-box;
            color: #fff;
            cursor: pointer;
            display: block;
            font-family: -apple-system, system-ui, "Segoe UI", "Liberation Sans",
              sans-serif;
            font-size: 13px;
            font-weight: 400;
            line-height: 1.15385;
            margin: 0 auto;
            outline: none;
            padding: 8px 0.8em;
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
            box-shadow: 0 0 0 4px rgba(0, 149, 255, 0.15);
          }

          .btn:active {
            background-color: #0064bd;
            box-shadow: none;
          }
        </style>
      </head>
      <body>
        <form action="/dipendenti/modificadipendente" method="get">
          <div class="main-form">
            <h1 align="center">
              Dettagli del dipendente numero <%= d.getId() %>
            </h1>
            ID
            <input
              type="text"
              name="id"
              value="<%= d.getId() %>"
              readonly
            /><br />
            NOME
            <input type="text" name="nome" value="<%= d.getNome() %>" /><br />
            SENIORITY
            <select class="select" name="seniority">
              <option value="Junior">Junior</option>
              <option value="Middle">Middle</option>
              <option value="Senior">Senior</option>
            </select>
            <br />
            <input
              type="hidden"
              name="password"
              value="<%= d.getPassword() %>"
            />
            PROJECTOWNER
            <select class="select" name="idprojectowner">
              <% for(ProjectOwner p : POs) {%>
              <option value="<%= p.getId() %>"><%= p.getNome()%></option>
              <%} %></select
            ><br />

            <input type="submit" value="AGGIORNA" />
          </div>
        </form>
        <a href="/projectowner/elencodipendentip">
          <button class="btn" role="button">ANNULLA</button></a
        >
      </body>
    </html></ProjectOwner
  ></ProjectOwner
>
