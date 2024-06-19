package modele;

import java.util.ArrayList;
import java.util.Objects;

public class Artiste {

    private String nom;
    private ArrayList<Spectacle>lesSpectacles = new ArrayList<>();
    private static int limite = 1;

    public Artiste(String nom,Spectacle spec) {
        this.nom = nom;
        this.getLesSpectacles().add(spec);
        spec.ajouterArtiste(this);
    }

    public void affiche(){
        System.out.println("Le nom est"+this.getNom());
        for (Spectacle spec : lesSpectacles){
            spec.afficheSpec();
        }
    }

    public void afficherSpect(){

    }

    public void afficheArt(){
        System.out.println("Le nom est"+this.getNom());
    }
    public void supprimerJoueDans(Spectacle spec){
        if(spec.equals(null)){
            System.out.println("Impossible de le supprimer il est null");
        }else if(!this.getLesSpectacles().contains(spec)){
            System.out.println("Le spectacles n'est pas contenu");
        }else if (this.getLesSpectacles().size() <= Artiste.limite ){
            System.out.println("Impossible de suprimer pas assez d'élément");
        }else{
            supprimerSpectacle(spec);
            spec.supprimerArtiste(this);
        }
    }

    private void supprimerSpectacle(Spectacle spec) {
        this.getLesSpectacles().remove(spec);
    }

    public void ajouterJoueDans(Spectacle spec){
        if(spec.equals(null)){
            System.out.println("Le tarif est null");
        }else if(this.getLesSpectacles().contains(spec)){
            System.out.println("Le spectacle est déja ajouté");
        }else{
            ajouterSpectacle(spec);
            spec.ajouterArtiste(this);
        }
    }

    protected void ajouterSpectacle(Spectacle spec){
        this.getLesSpectacles().add(spec);
    }

    public static int getLimite() {
        return limite;
    }

    public ArrayList<Spectacle> getLesSpectacles() {
        return lesSpectacles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artiste artiste = (Artiste) o;
        return Objects.equals(nom, artiste.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }
}
