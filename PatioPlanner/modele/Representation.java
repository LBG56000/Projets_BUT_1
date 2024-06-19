package modele;

import java.util.ArrayList;

public class Representation {
    private String jour, heure;
    private boolean annulee;
    private Spectacle spec;
    private ArrayList<Reservation>lesReservation = new ArrayList<>();

    public Representation(String jour, String heure, boolean annulee,Spectacle spec) {
        this.jour = jour;
        this.heure = heure;
        this.annulee = annulee;
        spec.ajouterRepre(this);
    }

    public void ajouterRepre(Spectacle spec){
        if(spec.equals(null)){
            System.out.println("Impossible");
        }else if(spec.getLesRepres().contains(this)){
            System.out.println("Impossible deja ajoutée");
        }else {
            this.spec = spec;
            spec.ajouterRepre(this);
        }
    }

    public void affiche(){

    }

    
    
    public Spectacle getSpec() {
		return spec;
	}

	public ArrayList<Reservation> getLesReservation() {
		return lesReservation;
	}

	public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    public void setAnnulee(boolean annulee) {
        this.annulee = annulee;
    }
}
