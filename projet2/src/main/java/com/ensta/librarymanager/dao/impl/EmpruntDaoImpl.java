package com.ensta.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.sql.Date;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.exceptions.DaoException;
import com.ensta.librarymanager.models.Abonnement;
import com.ensta.librarymanager.models.Emprunt;
import com.ensta.librarymanager.models.Livre;
import com.ensta.librarymanager.models.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntDaoImpl implements EmpruntDao{
    private static EmpruntDao instance;
    private EmpruntDaoImpl(){}

    public static EmpruntDao getInstance(){
        if(instance == null){ instance = new EmpruntDaoImpl(); }
        return instance;
    }

    private static final String SELECT_ALL_JOIN = "SELECT e.id as id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
                                                + "FROM emprunt AS e "
                                                + "INNER JOIN membre ON membre.id = e.idMembre "
                                                + "INNER JOIN livre ON livre.id = e.idLivre ";
    private static final String SELECT_ALL_QUERY = SELECT_ALL_JOIN
                                                 + "ORDER BY dateRetour DESC;";
    private static final String SELECT_CURRENT_QUERY = SELECT_ALL_JOIN
                                                     + "WHERE dateRetour IS NULL;";
    private static final String SELECT_CURRENT_BY_MEMBER_QUERY = SELECT_ALL_JOIN
                                                              + "WHERE dateRetour IS NULL AND membre.id = ?;";
    private static final String SELECT_CURRENT_BY_BOOK_QUERY = SELECT_ALL_JOIN
                                                              + "WHERE dateRetour IS NULL AND livre.id = ?;";
    private static final String SELECT_LOAN_QUERY = SELECT_ALL_JOIN
                                                  + "WHERE e.id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) "
                                             + "VALUES (?, ?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE emprunt "
                                             + "SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? "
                                             + "WHERE id = ?;";
	private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt;";
    
    @Override
    public List<Emprunt> getList() throws DaoException{
        List<Emprunt> emprunts = new ArrayList();
        ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			res = preparedStatement.executeQuery();
			while(res.next()) {
                Membre membre = new Membre(res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")));
                Livre livre = new Livre(res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
                Emprunt emprunt = new Emprunt(membre, livre, res.getDate("dateEmprunt").toLocalDate(), res.getDate("dateRetour").toLocalDate());
                emprunts.add(emprunt);
            }
            System.out.println("GET: " + emprunts);
        } catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts", e);
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
		return emprunts;
    }

    @Override
	public List<Emprunt> getListCurrent() throws DaoException{
        List<Emprunt> emprunts = new ArrayList();
        ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_CURRENT_QUERY);
			res = preparedStatement.executeQuery();
			while(res.next()) {
                Membre membre = new Membre(res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")));
                Livre livre = new Livre(res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
                Emprunt emprunt = new Emprunt(membre, livre, res.getDate("dateEmprunt").toLocalDate(), res.getDate("dateRetour").toLocalDate());
                emprunts.add(emprunt);
            }
            System.out.println("GET: " + emprunts);
        } catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts pas encore rendus", e);
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
		return emprunts;
    }

    @Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{
        List<Emprunt> emprunts = new ArrayList();
        ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_CURRENT_BY_MEMBER_QUERY);
            preparedStatement.setInt(1, idMembre);
			res = preparedStatement.executeQuery();
			while(res.next()) {
                Membre membre = new Membre(res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")));
                Livre livre = new Livre(res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
                Emprunt emprunt = new Emprunt(membre, livre, res.getDate("dateEmprunt").toLocalDate(), res.getDate("dateRetour").toLocalDate());
                emprunts.add(emprunt);
            }
            System.out.println("GET: " + emprunts);
        } catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts pas encore rendus pour un membre donne", e);
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
		return emprunts;
    }

    @Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{
        List<Emprunt> emprunts = new ArrayList();
        ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_CURRENT_BY_BOOK_QUERY);
            preparedStatement.setInt(1, idLivre);
			res = preparedStatement.executeQuery();
			while(res.next()) {
                Membre membre = new Membre(res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")));
                Livre livre = new Livre(res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
                Emprunt emprunt = new Emprunt(membre, livre, res.getDate("dateEmprunt").toLocalDate(), res.getDate("dateRetour").toLocalDate());
                emprunts.add(emprunt);
            }
            System.out.println("GET: " + emprunts);
        } catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts pas encore rendus pour un membre donne", e);
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
		return emprunts;
    }

    @Override
	public Emprunt getById(int id) throws DaoException{
        Emprunt emprunt = new Emprunt();
        ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_LOAN_QUERY);
            preparedStatement.setInt(1, id);
			res = preparedStatement.executeQuery();
			if(res.next()) {
                emprunt.setId(res.getInt("id"));
                Membre membre = new Membre(res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")));
                emprunt.setMembre(membre);
                Livre livre = new Livre(res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
                emprunt.setLivre(livre);
                emprunt.setDateEmprunt(res.getDate("dateEmprunt").toLocalDate());
                emprunt.setDateRetour(res.getDate("dateRetour").toLocalDate());
            }
            System.out.println("GET: " + emprunt);
        } catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste de l'emprunt: id="+id, e);
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
		return emprunt;
    }

    @Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{
        Membre membre = new Membre();
        Livre livre = new Livre();
        Emprunt emprunt = new Emprunt(membre, livre, dateEmprunt, null);
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = -1;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idMembre);
			preparedStatement.setInt(2, idLivre);
            preparedStatement.setDate(3, Date.valueOf(dateEmprunt));
            preparedStatement.setDate(4, null);
			preparedStatement.executeUpdate();
			res = preparedStatement.getGeneratedKeys();
			
			System.out.println("CREATE: " + emprunt);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation de l'emprunt: " + emprunt, e);
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
    }

    @Override
	public void update(Emprunt emprunt) throws DaoException{
        Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setInt(1, emprunt.getMembre().getId());
			preparedStatement.setInt(2, emprunt.getLivre().getId());
            preparedStatement.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
			preparedStatement.setDate(4, Date.valueOf(emprunt.getDateRetour()));
			preparedStatement.executeUpdate();

			System.out.println("UPDATE: " + emprunt);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la mise a jour de l'emprunt: " + emprunt, e);
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
			throw new DaoException("Probleme lors de la recuperation du nombre d'emprunts", e);
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