package modele.Tarification;

import java.util.Objects;

public class Groupe {
	private static double reduction; 

	public Groupe(double reduction) {
		super();
		this.reduction = reduction;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Groupe groupe = (Groupe) o;
		return Double.compare(getReduction(), groupe.getReduction()) == 0;
	}

	public String toString() {
		return "Groupe{" +
				"reduction=" + reduction +
				'}';
	}

	public void affiche(){
		System.out.println("Tarif : " + this.getReduction());
	}

	public double getReduction () {
		return reduction;
	}

	public void setReduction(double reduction) {
		this.reduction = reduction;
	} 
	
	

}
