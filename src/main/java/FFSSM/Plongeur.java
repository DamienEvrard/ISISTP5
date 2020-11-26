package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;

public class Plongeur extends Personne{
    
	public int niveau;
        public GroupeSanguin groupeSang;
        public ArrayList<Licence> licence;
        
        public Plongeur(String numINSEE, String nom, String prenom, String adresse, String tel, LocalDate dateN, int lvl, GroupeSanguin sang){
            super(numINSEE,nom,prenom,adresse,tel,dateN);
            this.niveau=lvl;
            this.groupeSang = sang;
            this.licence= new ArrayList<>();
        }
        
        
        public void ajouteLicence(String num, LocalDate date, Club club){
            this.licence.add(new Licence(this, num, date, niveau,club));
        }
        
        public Licence getLastLicence(){
            return this.licence.get(this.licence.size()-1);
        }  

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public GroupeSanguin getGroupeSang() {
        return groupeSang;
    }

    public void setGroupeSang(GroupeSanguin groupeSang) {
        this.groupeSang = groupeSang;
    }

    public ArrayList<Licence> getLicence() {
        return licence;
    }

    public void setLicence(ArrayList<Licence> licence) {
        this.licence = licence;
    }
        
        
}
