package com.ensta.librarymanager.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.services.MembreService;
import com.ensta.librarymanager.services.impl.MembreServiceImpl;
import com.ensta.librarymanager.exceptions.*;
import com.ensta.librarymanager.models.Membre;

@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet{
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        MembreService membreService = MembreServiceImpl.getInstance();
        Membre membre = new Membre();
        int id = -1;

		try {
			membre = membreService.getById(id);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

        request.setAttribute("id", membre.getId());
        request.setAttribute("prenom", membre.getPrenom());
        request.setAttribute("nom", membre.getNom());
        request.setAttribute("adresse", membre.getAdresse());
        request.setAttribute("email", membre.getEmail());
        request.setAttribute("telephone", membre.getTelephone());
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		dispatcher.forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
