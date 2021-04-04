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

@WebServlet("/emprunt_return")
public class EmpruntReturnServlet extends HttpServlet{
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();

        List<Emprunt> emprunts = new ArrayList<>();

		try{
            emprunts = empruntService.getListCurrent();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("loans", emprunts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
        dispatcher.forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
