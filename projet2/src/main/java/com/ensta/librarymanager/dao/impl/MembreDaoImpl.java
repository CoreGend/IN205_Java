package com.ensta.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.exceptions.DaoException;
import com.ensta.librarymanager.models.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;
import com.ensta.librarymanager.models.Abonnement;

public class MembreDaoImpl implements MembreDao{
    private static MembreDao instance;
    private MembreDaoImpl(){}

    public static MembreDao getInstance(){
        if(instance == null) { instance = new MembreDaoImpl(); }
        return instance;
    }

    private static final String CREATE_QUERY = "INSERT INTO membre (nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_ONE_QUERY = "SELECT * FROM membre WHERE id=?;";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM membre ORDER BY nom, prenom;";
	private static final String UPDATE_QUERY = "UPDATE membre SET nom=?,prenom=?,adresse=?,email=?,telephone=?,abonnement=? WHERE id=?;";
	private static final String DELETE_QUERY = "DELETE FROM membre WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM membre;";

    @Override
	public List<Membre> getList() throws DaoException{
        List<Membre> membres = new ArrayList<>();
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            res = preparedStatement.executeQuery();
            while(res.next()){
                Membre membre = new Membre(res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")));
                membres.add(membre);
            }
            System.out.println("GET: " + membres);
        } catch(SQLException e){
            throw new DaoException("Probleme lors de la recuperation de la liste des membres");
        } finally {
            try{ res.close(); }
            catch(Exception e) { e.printStackTrace(); }
            try{ preparedStatement.close(); }
            catch(Exception e) { e.printStackTrace(); }
            try{ connection.close(); }
            catch(Exception e) { e.printStackTrace(); }
        }
        return membres;
    }

    @Override
	public Membre getById(int id) throws DaoException{
        Membre membre = new Membre();
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
			preparedStatement.setInt(1, id);
			res = preparedStatement.executeQuery();
			if(res.next()) {
                membre.setId(res.getInt("id"));
                membre.setPrenom(res.getString("prenom"));
                membre.setNom(res.getString("nom"));
                membre.setEmail(res.getString("email"));
                membre.setTelephone(res.getString("telephone"));
				membre.setAbonnement(Abonnement.valueOf(res.getString("abonnement")));
            }
            System.out.println("GET: " + membre);
        } catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation du membre: id=" + id, e);
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
		return membre;
	}

    @Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException{
        Membre membre = new Membre(nom, prenom, adresse, email, telephone, Abonnement.BASIC);
        ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = -1;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, adresse);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, telephone);
			preparedStatement.executeUpdate();
			res = preparedStatement.getGeneratedKeys();
			if(res.next()){
				id = res.getInt(1);
			}
			System.out.println("CREATE: " + membre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation du membre: " + membre, e);
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
	public void update(Membre membre) throws DaoException{
        Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, membre.getPrenom());
			preparedStatement.setString(2, membre.getNom());
            preparedStatement.setString(3, membre.getAdresse());
			preparedStatement.setString(4, membre.getEmail());
            preparedStatement.setString(5, membre.getTelephone());
            preparedStatement.setString(6, membre.getAbonnement().name());
            preparedStatement.setInt(7, membre.getId());            
			preparedStatement.executeUpdate();

			System.out.println("UPDATE: " + membre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la mise a jour du livre: " + membre, e);
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
	public void delete(int id) throws DaoException
    {
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

	public int count() throws DaoException{
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
			throw new DaoException("Probleme lors de la recuperation de la liste des membres", e);
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