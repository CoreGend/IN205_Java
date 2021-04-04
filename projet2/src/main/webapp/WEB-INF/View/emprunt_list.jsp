<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ensta.librarymanager.models.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%! private List<Emprunt> emprunts = new ArrayList<>();%>
<% emprunts = (List) request.getAttribute("loans"); %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Liste des emprunts</h1>
      </div>
      <div class="row">
        <div class="container">
	        <div class="col s12">
	          <table class="striped">
                <thead>
                    <tr>
                        <th>Livre</th>
                        <th>Membre emprunteur</th>
                        <th>Date d'emprunt</th>
                        <th>Retour</th>
                    </tr>
                </thead>
                <tbody id="results">
                  <% if(!emprunts.isEmpty()) {
                    for(Emprunt e : emprunts) { 
                      Livre l = e.getLivre();
                      Membre m = e.getMembre(); %>
                      <tr>
                          <td><%= l.getTitre() %>, <em>de <%= l.getAuteur() %></em></td>
                          <td><%= m.getPrenom() %> <%= m.getNom() %></td>
                          <td><%= e.getDateEmprunt() %></td>
                          <td>
                            <% if(e.getDateRetour() == null) { %>
                              <a href="emprunt_return?id=idDeLEmprunt"><ion-icon class="table-item" name="log-in"></a>
                            <% } else {
                              e.getDateRetour();
                            } %>
                          </td>
                    </tr>
                    <% }
                  } %>

					 <!-- TODO : parcourir la liste des emprunts en cours et les afficher selon la structure d'exemple ci-dessus -->
					 <!-- TODO : dans le champ "retour", afficher la date de retour si elle existe, et un lien vers la page de retour si la date est vide (comme dans l'exemple ci-dessus) -->
                </tbody>
            </table>
          </div>
        </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
