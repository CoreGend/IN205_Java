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

@WebServlet("/membre_list")
public class MembreListServlet extends HttpServlet{
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		MembreService membreService = MembreServiceImpl.getInstance();
        List<Membre> membres = new ArrayList<>();
        try{
            membres = membreService.getList();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        request.setAttribute("membres", membres);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_list.jsp");
        dispatcher.forward(request, response);
	}
}
