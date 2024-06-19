package modele.Genre;

public abstract class Genre {
	private String libelle;

	public Genre(String libelle) {
		super();
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	} 
	
	
}
