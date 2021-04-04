package com.ensta.librarymanager.services.impl;

import java.util.List;
import java.util.ArrayList;

import com.ensta.librarymanager.services.LivreService;
import com.ensta.librarymanager.services.EmpruntService;
import com.ensta.librarymanager.services.impl.EmpruntServiceImpl;
import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.dao.impl.LivreDaoImpl;
import com.ensta.librarymanager.exceptions.DaoException;
import com.ensta.librarymanager.exceptions.ServiceException;
import com.ensta.librarymanager.models.Livre;

public class LivreServiceImpl implements LivreService{
    private static LivreService instance;
    private LivreServiceImpl(){}
    public static LivreService getInstance(){
        if(instance == null) { instance = new LivreServiceImpl(); }
        return instance;
    }

    @Override
	public List<Livre> getList() throws ServiceException{
        LivreDao livreDao = LivreDaoImpl.getInstance();
        List<Livre> livres = new ArrayList<>();
        try{
            livres = livreDao.getList();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return livres;
    }

    @Override
	public List<Livre> getListDispo() throws ServiceException{
        LivreDao livreDao = LivreDaoImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        int size = -1;
        List<Livre> livres = new ArrayList<>();
        try{
            size = livreDao.count();
            for(int idLivre=0; idLivre<size; idLivre++){
                if(empruntService.isLivreDispo(idLivre))
                { 
                    Livre livre = new Livre();
                    livre = livreDao.getById(idLivre);
                    livres.add(livre);
                }
            }
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return livres;
    }
	
    @Override
    public Livre getById(int id) throws ServiceException{
        LivreDao livreDao = LivreDaoImpl.getInstance();
        Livre livre = new Livre();
        try{
            livre = livreDao.getById(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return livre;
    }

    @Override
	public int create(String titre, String auteur, String isbn) throws ServiceException{
        if(titre == null || titre == ""){
            throw new ServiceException("Title should not be empty");
        }
        LivreDao livreDao = LivreDaoImpl.getInstance();
        int i=-1;
        try{
            i = livreDao.create(titre, auteur, isbn);
        } catch (DaoException e){
            System.out.println(e.getMessage());
        }
        return i;
    }

    @Override
	public void update(Livre livre) throws ServiceException{
        if(livre.getTitre() == null || livre.getTitre() == ""){
            throw new ServiceException("Title should not be empty");
        }
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try{
            livreDao.update(livre);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
	public void delete(int id) throws ServiceException{
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try{
            livreDao.delete(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }

	public int count() throws ServiceException{
        int count = -1;
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try{
            count = livreDao.count();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
