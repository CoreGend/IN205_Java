package com.ensta.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exceptions.DaoException;
import com.ensta.librarymanager.models.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao{
    private static LivreDao instance;
    private LivreDaoImpl(){}

    public static LivreDao getInstance(){
        if(instance == null){ instance = new LivreDaoImpl(); }
        return instance;
    }

    private static final String CREATE_QUERY = "INSERT INTO livre (titre, auteur, isbn) VALUES (?, ?, ?);";
	private static final String SELECT_ONE_QUERY = "SELECT * FROM livre WHERE id=?;";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM livre;";
	private static final String UPDATE_QUERY = "UPDATE livre SET titre=?, auteur=?, isbn=? WHERE id=?;";
	private static final String DELETE_QUERY = "DELETE FROM livre WHERE id=?;";
	private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM livre;";

	@Override
	public List<Livre> getList() throws DaoException {
		List<Livre> livres = new ArrayList<>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			res = preparedStatement.executeQuery();
			while(res.next()) {
				Livre livre = new Livre(res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
				livres.add(livre);
			}
			System.out.println("GET: " + livres);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des livres", e);
		} finally {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return livres;
	}

    @Override
	public Livre getById(int id) throws DaoException {
		Livre livre = new Livre();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
			preparedStatement.setInt(1, id);
			res = preparedStatement.executeQuery();
			if(res.next()) {
				livre.setId(res.getInt("id"));
				livre.setTitre(res.getString("titre"));
				livre.setAuteur(res.getString("auteur"));
                livre.setIsbn(res.getString("isbn"));
			}
			
			System.out.println("GET: " + livre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation du livre: id=" + id, e);
		} finally {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return livre;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException {
		Livre livre = new Livre(titre, auteur, isbn);
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = -1;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, titre);
			preparedStatement.setString(2, auteur);
            preparedStatement.setString(3, isbn);
			preparedStatement.executeUpdate();
			res = preparedStatement.getGeneratedKeys();
			if(res.next()){
				id = res.getInt(1);
			}

			System.out.println("CREATE: " + livre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation du livre: " + livre, e);
		} finally {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}

    @Override
	public void update(Livre livre) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, livre.getTitre());
			preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getIsbn());
			preparedStatement.setInt(4, livre.getId());
			preparedStatement.executeUpdate();

			System.out.println("UPDATE: " + livre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la mise a jour du livre: " + livre, e);
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

    @Override
	public void delete(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			System.out.println("DELETE: " + id);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la suppression du livre: " + id, e);
		}  finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int count() throws DaoException {
		int result=-1;
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(COUNT_QUERY);
			res = preparedStatement.executeQuery();
			if(res.next()) {
				result = res.getInt("count");
			}
			System.out.println("COUNT: " + result);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des livres", e);
		} finally {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}