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

import com.ensta.librarymanager.services.EmpruntService;
import com.ensta.librarymanager.services.impl.EmpruntServiceImpl;
import com.ensta.librarymanager.exceptions.*;
import com.ensta.librarymanager.models.Emprunt;

@WebServlet("/emprunt_list")
public class EmpruntListServlet extends HttpServlet{
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();

        String showParameter = request.getParameter("show");

		try{
            if(showParameter.equals("all")){
                emprunts = empruntService.getList();
            } else {
                emprunts = empruntService.getListCurrent();
            }

        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("loans", emprunts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
        dispatcher.forward(request, response);
	}
}
