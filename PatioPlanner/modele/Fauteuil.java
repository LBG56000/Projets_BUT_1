package modele;

import java.util.ArrayList;

public class Fauteuil {
    private String rangee, numero;
    private Zone maZone;
    private ArrayList<Billet> mesBillets = new ArrayList<>();

    public Fauteuil(String rangee, String numero, Zone maZone) {
        this.rangee = rangee;
        this.numero = numero;
        this.maZone = maZone;
    }

    public void affiche(){
        System.out.println("Rangée : " + this.rangee + "\nNuméro : " + this.numero);
        this.maZone.affiche();
        if (!this.mesBillets.isEmpty()){
            int i = 1;
            for (Billet billet : mesBillets) {
                System.out.println("Billet n°" + i);
                billet.affiche();
                i++;
            }
        }
    }

    public void ajouterPlacement(Billet billet){
        if (!billet.equals(null) && !mesBillets.contains(billet)){
            ajouterBillet(billet);
        }
    }

    public void retirerPlacement(Billet billet){
        if (!billet.equals(null) && mesBillets.contains(billet)){
            retirerBillet(billet);
        }
    }

    public void modifierPlacement(Billet billet1, Billet billet2){
        if (!billet2.equals(null) && mesBillets.contains(billet1)){
            for (Billet b : mesBillets) {
                if (b == billet1){
                    modifierBillet(b, billet2);
                }
            }
        }
    }

    protected void ajouterBillet(Billet billet){
        this.mesBillets.add(billet);
    }

    protected void retirerBillet(Billet billet){
        this.mesBillets.remove(billet);
    }

    protected void modifierBillet(Billet billet1, Billet billet2){
        billet1 = billet2;
    }

    public String getRangee() {
        return rangee;
    }

    public void setRangee(String rangee) {
        this.rangee = rangee;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Zone getMaZone() {
        return maZone;
    }

    public void setMaZone(Zone maZone) {
        this.maZone = maZone;
    }

    public ArrayList<Billet> getMesBillets() {
        return mesBillets;
    }
}
