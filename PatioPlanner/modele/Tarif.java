package modele;

import java.util.Objects;

public class Tarif {

    private double pleinTarif;

    public Tarif(double pleinTarif) {
        this.pleinTarif = pleinTarif;
    }

    public void supprimerTarif(Zone zone, Spectacle spec){
        if(zone.equals(null)){
            System.out.println("La zone est null");
        }else if(spec.equals(null)){
            System.out.println("Le spectacle est null");
        }else if(!zone.getLeTarifZone().containsKey(spec)){
            System.out.println("Le spectacle n'est ajouté");
        }else if(!spec.getLeTarifSpectacle().containsKey(zone)){
            System.out.println("Impossible la zone est déjà ajoutée");

        }else if(zone.getLeTarifZone().size() <= 1){
            System.out.println("Impossible pas asse de valeur");
        }else if (spec.getLeTarifSpectacle().size() <= 1 ){
            System.out.println("Impossible");
        }else{
            zone.supprimerZoneTarif(spec,this);
            spec.supprimerSpecTarif(zone,this);
        }
    }
    public void ajouterTarif(Zone zone, Spectacle spec){
        if(zone.equals(null)){
            System.out.println("La zone est null");
        }else if(spec.equals(null)){
            System.out.println("Le spectacle est null");
        }else if(zone.getLeTarifZone().containsKey(spec)){
            System.out.println("Le spectacle est déja ajoutée");
        }else if(spec.getLeTarifSpectacle().containsKey(zone)){
            System.out.println("Impossible la zone est déjà ajoutée");
        }else{
            zone.ajouterZoneTarif(spec,this);
            spec.ajouterSpecTarif(zone,this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarif tarif = (Tarif) o;
        return Double.compare(pleinTarif, tarif.pleinTarif) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pleinTarif);
    }

    public double getPleinTarif() {
        return pleinTarif;
    }

    public void setPleinTarif(double pleinTarif) {
        this.pleinTarif = pleinTarif;
    }
}
