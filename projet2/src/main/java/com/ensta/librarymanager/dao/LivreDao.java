package com.ensta.librarymanager.dao;

import java.util.List;

import com.ensta.librarymanager.exceptions.DaoException;
import com.ensta.librarymanager.models.Livre;

public interface LivreDao {
	public List<Livre> getList() throws DaoException;
	public Livre getById(int id) throws DaoException;
	public int create(String titre, String auteur, String isbn) throws DaoException;
	public void update(Livre livre) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
