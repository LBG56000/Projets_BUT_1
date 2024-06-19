package modele.Tarification;

import java.util.Objects;

public class Jeune extends Tarification {
	
	private static double reduction; 

	public Jeune(double reduction) {
		super();
		this.reduction = reduction;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Jeune jeune = (Jeune) o;
		return Double.compare(getReduction(), jeune.getReduction()) == 0;
	}

	public String toString() {
		return "Jeune{" +
				"reduction=" + reduction +
				'}';
	}

	public void affiche(){
		System.out.println("Tarif : " + this.getReduction());
	}

	public double getReduction() {
		return reduction;
	}

	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
	
	

}
