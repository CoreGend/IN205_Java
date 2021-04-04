package com.ensta.librarymanager.models;

import java.time.LocalDate;

public class Emprunt
{
    private int id;
    private Membre membre;
    private Livre livre;
    private java.time.LocalDate dateEmprunt;
    private java.time.LocalDate dateRetour;
    private static int nbEmprunts = 0;
    
    public Emprunt(){super();}
	public Emprunt(Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour) {
		id = nbEmprunts; nbEmprunts++;
		this.membre = membre;
		this.livre = livre;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}

	@Override
	public String toString() {
		return "Emprunt [id=" + id + ", livre=" + livre + ", dateEmprunt=" + dateEmprunt + ", dateRetour=" + dateRetour
				+ "]";
	}

    public int getId(){ return id; }
    public Membre getMembre(){ return membre; }
    public Livre getLivre(){ return livre; }
    public java.time.LocalDate getDateEmprunt(){ return dateEmprunt; }
    public java.time.LocalDate getDateRetour(){ return dateRetour; }

    public void setId(int id){ this.id = id; }
    public void setMembre(Membre membre){ this.membre = membre; }
    public void setLivre(Livre livre){ this.livre = livre; }
    public void setDateEmprunt(java.time.LocalDate dateEmprunt){ this.dateEmprunt = dateEmprunt; }
    public void setDateRetour(java.time.LocalDate dateRetour){ this.dateRetour = dateRetour; }
}