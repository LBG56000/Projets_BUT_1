package modele.Client;

public class Abonne extends Client {

	public Abonne(String nom, String prenom,String adresse, String numTel, String mail, String numero,String ville) {
		super(nom, prenom,adresse, numTel, mail, numero,ville);
	}

	public void affiche(){
		System.out.println("Ceci est un test"+this.getMail());

	}


}
