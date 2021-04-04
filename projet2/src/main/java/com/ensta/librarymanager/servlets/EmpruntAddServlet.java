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

import com.ensta.librarymanager.services.*;
import com.ensta.librarymanager.services.impl.*;
import com.ensta.librarymanager.exceptions.*;
import com.ensta.librarymanager.models.*;

@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet{
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        LivreService livreService = LivreServiceImpl.getInstance();
        MembreService membreService = MembreServiceImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();

        List<Livre> livresDispo = new ArrayList<>();
        List<Membre> membresPossible = new ArrayList<>();

		try{
            livresDispo = livreService.getListDispo();
            membresPossible = membreService.getListMembreEmpruntPossible();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("membres", membresPossible);
        request.setAttribute("books", livresDispo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
        dispatcher.forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
