package com.ensta.librarymanager.dao;

import java.util.List;

import com.ensta.librarymanager.exceptions.DaoException;
import com.ensta.librarymanager.models.Membre;

public interface MembreDao {
	public List<Membre> getList() throws DaoException;
	public Membre getById(int id) throws DaoException;
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException;
	public void update(Membre membre) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
