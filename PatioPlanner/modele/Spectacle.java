package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Spectacle {

    private String nom;
    private int duree;
    private int nbreMaxSpect;
    private String genre;
    private ArrayList<Artiste>lesArtistes = new ArrayList<>();
    private HashMap<Zone,Tarif>leTarifSpectacle =new HashMap<>();
    private Zone laZone;
    private ArrayList<Representation>lesRepres = new ArrayList<>();


    public Spectacle(String nom, int duree, int nbreMaxSpect, String genre) {
        this.nom = nom;
        this.duree = duree;
        this.nbreMaxSpect = nbreMaxSpect;
        this.genre = genre;
    }

    protected void ajouterRepre(Representation repre) {
        this.getLesRepres().add(repre);
    }


    public void ajouterTarifSpectacle(Tarif t,Zone zone){
        this.getLeTarifSpectacle().put(zone,t);
    }
    public void ajouterArtiste(Artiste art){
        this.getLesArtistes().add(art);
    }
    public String getNom() {
        return nom;
    }

    public ArrayList<Representation> getLesRepres() {
        return lesRepres;
    }

    public Zone getLaZone() {
        return laZone;
    }

    protected void setLaZone(Zone laZone) {
        this.laZone = laZone;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public HashMap<Zone, Tarif> getLeTarifSpectacle() {
        return leTarifSpectacle;
    }



    public int getDuree() {
        return duree;
    }


    public void afficheleTarifSpectacle(){

    }
    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNbreMaxSpect() {
        return nbreMaxSpect;
    }

    public void setNbreMaxSpect(int nbreMaxSpect) {
        this.nbreMaxSpect = nbreMaxSpect;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<Artiste> getLesArtistes() {
        return lesArtistes;
    }

    public void supprimerArtiste(Artiste art) {
        this.getLesArtistes().remove(art);
    }

    public void afficheTout(){
        afficheArtiste();
        afficheSpec();
    }
    public void afficheArtiste() {
        for(Artiste art : lesArtistes){
            art.afficheArt();
        }
    }

    public void afficheSpec() {
        System.out.println("Le nom est"+this.getNom());
        System.out.println("La duree est"+this.getDuree());
        System.out.println("Le nombre max est de spectateur est"+this.getNbreMaxSpect());
        System.out.println("Le genre est"+this.getGenre());
    }

    protected void ajouterSpecTarif(Zone zone, Tarif tarif) {
        this.getLeTarifSpectacle().put(zone,tarif);
    }

    protected void supprimerSpecTarif(Zone zone, Tarif tarif) {
        this.getLeTarifSpectacle().remove(zone,tarif);
    }
}
