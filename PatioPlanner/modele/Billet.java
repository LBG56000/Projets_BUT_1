package modele;

import java.util.Objects;

import modele.Tarification.Tarification;

public class Billet {
    private String numero;
    private modele.Tarification.Tarification monTarif;
    private Fauteuil monFauteuil;

    public Billet(String numero, Tarification monTarif, Fauteuil monFauteuil) {
        this.numero = numero;
        this.monTarif = monTarif;
        this.monFauteuil = monFauteuil;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Billet billet = (Billet) o;
        return Objects.equals(getNumero(), billet.getNumero());
    }

    public String toString() {
        return "Billet{" +
                "numero='" + numero + '\'' +
                ", monTarif=" + monTarif +
                '}';
    }

    public void affiche() {
        System.out.println("Billet numéro : " + this.numero);
        this.monTarif.affiche();
        this.monFauteuil.affiche();
        System.out.println("kjbg");
    }

    public void modifierTarif(Tarification tarif){
        if (!tarif.equals(null) && !tarif.equals(this.monTarif)){
            ajouterTarif(tarif);
        }
    }

    public void modifierFauteuil(Fauteuil fauteuil){
        if (!fauteuil.equals(null) && !fauteuil.equals(this.monFauteuil)){
            ajouterFauteuil(fauteuil);
        }
    }
    
    

    public modele.Tarification.Tarification getMonTarif() {
		return monTarif;
	}

	public Fauteuil getMonFauteuil() {
		return monFauteuil;
	}

	protected void ajouterTarif(Tarification tarif){
        this.monTarif = tarif;
    }

    protected void ajouterFauteuil(Fauteuil fauteuil){
        this.monFauteuil = fauteuil;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
