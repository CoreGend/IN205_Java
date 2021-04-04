package com.ensta.librarymanager.services.impl;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.ensta.librarymanager.services.EmpruntService;
import com.ensta.librarymanager.services.LivreService;
import com.ensta.librarymanager.services.impl.LivreServiceImpl;
import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.dao.impl.EmpruntDaoImpl;
import com.ensta.librarymanager.exceptions.DaoException;
import com.ensta.librarymanager.exceptions.ServiceException;
import com.ensta.librarymanager.models.Emprunt;
import com.ensta.librarymanager.models.Membre;
import com.ensta.librarymanager.models.Livre;

public class EmpruntServiceImpl implements EmpruntService{
    private static EmpruntService instance;
    private EmpruntServiceImpl(){}
    public static EmpruntService getInstance(){
        if(instance == null) { instance = new EmpruntServiceImpl(); }
        return instance;
    }

    @Override
    public List<Emprunt> getList() throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try{
            emprunts = empruntDao.getList();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return emprunts;
    }

    @Override
	public List<Emprunt> getListCurrent() throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try{
            emprunts = empruntDao.getListCurrent();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return emprunts;
    }

    @Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try{
            emprunts = empruntDao.getListCurrentByMembre(idMembre);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return emprunts;
    }

    @Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try{
            emprunts = empruntDao.getListCurrentByLivre(idLivre);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return emprunts;
    }

    @Override
	public Emprunt getById(int id) throws ServiceException{
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        Emprunt emprunt = new Emprunt();
        try{
            emprunt = empruntDao.getById(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return emprunt;
    }

    @Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException{ 
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try{
            empruntDao.create(idMembre, idLivre, dateEmprunt);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    @Override
    public void returnBook(int id) throws ServiceException{ 
        
    }

    @Override
	public int count() throws ServiceException { 
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        int count = -1;
        try{
            count = empruntDao.count();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    @Override
	public boolean isLivreDispo(int idLivre) throws ServiceException { 
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        boolean dispo = true;
        try{
            emprunts = empruntDao.getListCurrentByLivre(idLivre);
            int size = emprunts.size();
            for(int i=0; i<size; i++){
                if(emprunts.get(i).getDateRetour() == null){ dispo = false; }
            }
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return dispo;
    }
    
    @Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException{ return false; }
}
