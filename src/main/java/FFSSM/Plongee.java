/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.graalvm.compiler.nodes.java.ArrayLengthNode;

public class Plongee {

	public Site lieu;

	public Moniteur chefDePalanquee;

	public LocalDate date;

	public int profondeur;

	public int duree;
        
        public ArrayList<Plongeur> participant;

	public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
                this.participant=new ArrayList<>();
	}

	public void ajouteParticipant(Plongeur participant) {
		this.participant.add(participant);
	}

	public LocalDate getDate() {
		return date;
	}

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme() {
            if(!participant.isEmpty()){
                for(Plongeur p : participant){
                     if(!p.getLastLicence().estValide(this.getDate())){
                         return false;
                     }
                 }
            }
            return true;
	}

    public Site getLieu() {
        return lieu;
    }

    public void setLieu(Site lieu) {
        this.lieu = lieu;
    }

    public Moniteur getChefDePalanquee() {
        return chefDePalanquee;
    }

    public void setChefDePalanquee(Moniteur chefDePalanquee) {
        this.chefDePalanquee = chefDePalanquee;
    }

    public int getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public ArrayList<Plongeur> getParticipant() {
        return participant;
    }

    public void setParticipant(ArrayList<Plongeur> participant) {
        this.participant = participant;
    }
        
        

}
