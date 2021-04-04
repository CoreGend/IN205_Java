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

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		MembreService membreService = MembreServiceImpl.getInstance();
        LivreService livreService = LivreServiceImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();

        List<Emprunt> emprunts = new ArrayList<Emprunt>();

        int memCount = -1; int bookCount = -1; int loanCount = -1;
        try{
            memCount = membreService.count();
            bookCount = livreService.count();
            loanCount = empruntService.count();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        try { emprunts = empruntService.getList(); } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("memCount", memCount);
        request.setAttribute("bookCount", bookCount);
        request.setAttribute("loanCount", loanCount);
        request.setAttribute("loans", emprunts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
        dispatcher.forward(request, response);
	}
}