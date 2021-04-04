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

import com.ensta.librarymanager.services.LivreService;
import com.ensta.librarymanager.services.impl.LivreServiceImpl;
import com.ensta.librarymanager.exceptions.*;
import com.ensta.librarymanager.models.Livre;

@WebServlet("/livre_add")
public class LivreAddServlet extends HttpServlet{
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        LivreService livreService = LivreServiceImpl.getInstance();

        String inputTitre = request.getParameter("titre");
        String inputAuteur = request.getParameter("auteur");
        String inputIsbn = request.getParameter("isbn");

		try{
            int id = livreService.create(inputTitre, inputAuteur, inputIsbn);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
        dispatcher.forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
