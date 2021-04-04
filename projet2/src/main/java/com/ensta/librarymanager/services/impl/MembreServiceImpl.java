package com.ensta.librarymanager.services.impl;

import java.util.List;
import java.util.ArrayList;

import com.ensta.librarymanager.exceptions.ServiceException;
import com.ensta.librarymanager.models.Membre;
import com.ensta.librarymanager.services.MembreService;
import com.ensta.librarymanager.services.EmpruntService;
import com.ensta.librarymanager.services.impl.EmpruntServiceImpl;
import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.dao.impl.MembreDaoImpl;
import com.ensta.librarymanager.exceptions.DaoException;

public class MembreServiceImpl implements MembreService {
    private static MembreService instance;
    private MembreServiceImpl(){}
    public static MembreService getInstance(){
        if(instance == null) { instance = new MembreServiceImpl(); }
        return instance;
    }

    @Override
	public List<Membre> getList() throws ServiceException{
        MembreDao membreDao = MembreDaoImpl.getInstance();
        List<Membre> membres = new ArrayList<>();
        try{
            membres = membreDao.getList();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return membres;
    }

    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException{
        MembreDao membreDao = MembreDaoImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        int size = -1;
        List<Membre> membres = new ArrayList<>();
        try{
            size = membreDao.count();
            for(int idMembre = 0; idMembre<size; idMembre++){
                if(empruntService.isEmpruntPossible(membreDao.getById(idMembre))){
                    Membre membre = new Membre();
                    membre = membreDao.getById(idMembre);
                    membres.add(membre);
                }
            }
        } catch (DaoException e){
            System.out.println(e.getMessage());
        }
        return membres;
    }
	

    @Override
    public Membre getById(int id) throws ServiceException
    {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        Membre membre = new Membre();
        try{
            membre = membreDao.getById(id);
        }
        catch(DaoException e){
            System.out.println(e.getMessage());
        }
        return membre;
    }

    @Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException
    {
        if(nom == null || nom == "" || prenom == null || prenom == ""){
            throw new ServiceException("Name and surname should not be empty");
        }
        MembreDao membreDao = MembreDaoImpl.getInstance();
        int i=-1;
        try{
            i = membreDao.create(nom.toUpperCase(), prenom, adresse, email, telephone);
        } catch (DaoException e){
            System.out.println(e.getMessage());
        }
        return i;
    }

    @Override
	public void update(Membre membre) throws ServiceException
    {
        if(membre.getNom() == null || membre.getNom() == "" || membre.getPrenom() == null || membre.getPrenom() == ""){
            throw new ServiceException("Name and surname should not be empty");
        }
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try{
            membre.setNom(membre.getNom().toUpperCase());
            membreDao.update(membre);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
	public void delete(int id) throws ServiceException{
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try{
            membreDao.delete(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
	public int count() throws ServiceException{
        int count = -1;
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try{
            count = membreDao.count();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

}
