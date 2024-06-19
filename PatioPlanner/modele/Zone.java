package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Zone {

    private String nom;
    private HashMap<Spectacle,Tarif>leTarifZone = new HashMap<>();

    public Zone(String nom,Spectacle spec) {
        this.nom = nom;
        spec.setLaZone(this);
    }

    public void affiche(){
        System.out.println("Le nom de la zone"+this.getNom());
        this.leTarifZone.forEach((spectacle, tarif) ->  {
            System.out.println(spectacle);
            System.out.println(tarif);
        });
    }

    public HashMap<Spectacle, Tarif> getLeTarifZone() {
        return leTarifZone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    protected void ajouterZoneTarif(Spectacle spec, Tarif tarif) {
        this.getLeTarifZone().put(spec,tarif);
    }

    protected void supprimerZoneTarif(Spectacle spec, Tarif tarif) {
        this.getLeTarifZone().remove(spec,tarif);
    }
}
