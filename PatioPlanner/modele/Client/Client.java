package modele.Client;

public abstract class Client {	
	
	private String nom; 
	private String prenom;
	private String adresse;
	private String numTel;
	private String mail; 
	private String numero;
	private String ville;
	
	public Client(String nom, String prenom,String adresse, String numTel, String mail, String numero,String ville) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTel = numTel;
		this.mail = mail;
		this.numero = numero;
		this.ville = ville;
	}

	
	
	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNumTel() {
		return numTel;
	}

	public String getMail() {
		return mail;
	}

	public String getNumero() {
		return numero;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	} 
	
	

}
