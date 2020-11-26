/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    private ArrayList<Embauche> emplois;
    private Optional<Club> employeur;

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int numeroDiplome,int lvl, GroupeSanguin sang) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance,lvl,sang);
        this.numeroDiplome = numeroDiplome;
        this.emplois = new ArrayList<>();
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() throws Exception {
         if (employeur.isPresent()){
             return this.employeur;
         }
         else{
             throw new Exception("Pas d'employeur");
         }
    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {
        
        this.emplois.add(new Embauche(debutNouvelle, this, employeur));
        this.employeur = Optional.of(employeur);
    }
    
    public void terminerEmbauche(LocalDate fin){
        this.emplois.get(this.emplois.size()-1).setFin(fin);
    }

    public List<Embauche> emplois() {
        return emplois;
    }

    public int getNumeroDiplome() {
        return numeroDiplome;
    }

    public void setNumeroDiplome(int numeroDiplome) {
        this.numeroDiplome = numeroDiplome;
    }

    public ArrayList<Embauche> getEmplois() {
        return emplois;
    }

    public void setEmplois(ArrayList<Embauche> emplois) {
        this.emplois = emplois;
    }

    public Optional<Club> getEmployeur() {
        return employeur;
    }

    public void setEmployeur(Optional<Club> employeur) {
        this.employeur = employeur;
    }

    
}
