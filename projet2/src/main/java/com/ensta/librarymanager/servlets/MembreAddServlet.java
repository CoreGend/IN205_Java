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

@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet{
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        MembreService membreService = MembreServiceImpl.getInstance();

        String inputNom = request.getParameter("nom");
        String inputPrenom = request.getParameter("prenom");
        String inputAdresse = request.getParameter("adresse");
        String inputEmail = request.getParameter("email");
        String inputTelephone = request.getParameter("telephone");

		try{
            int id = membreService.create(inputNom, inputPrenom, inputAdresse, inputEmail, inputTelephone);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
        dispatcher.forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
