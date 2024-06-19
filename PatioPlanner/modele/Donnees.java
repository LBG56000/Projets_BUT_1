package modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Annulation.*;
import modele.Client.*;
import modele.Genre.*;
import modele.Paiement.*;
import modele.Tarification.*;
import java.util.Date;

public class Donnees {
	static private ObservableList<Reservation> 	lesReservations = FXCollections.observableArrayList();

	public static void init () throws ParseException {
		// TODO Auto-generated method stub
		Abonne a1 = new Abonne("Toullec","Antoine","25 rue des cordiers","0626097064","antoinetoullec9@gmail.com","0001","Lorient");
		Abonne a2 = new Abonne("Faver","Sasha","Intermarché contact","0612345678","sashafaver@gmail.com","0002","rennes");
		Abonne a3 = new Abonne("Gourlaouen","Cyril","centre ville","0687456123","cyrilgourlaouen@gmail.com","0003","Paris");
		Abonne a4 = new Abonne("Bruno-Gache","Léo","Crous-land","0600112233","leobrunogache@gmail.com","0004","Lisbonne");
		Abonne a5 = new Abonne("Macron","Emmanuel","Elysée","0600000000","manumac@yahoo.es","0005","Paris");
		Abonne a6 = new Abonne("Macron","Emmanuel","Elysée","0600000000","manumac@yahoo.es","0005","Paris");
		Abonne a7 = new Abonne("Macron","Emmanuel","Elysée","0600000000","manumac@yahoo.es","0005","Paris");
		Abonne a8 = new Abonne("Macron","Emmanuel","Elysée","0600000000","manumac@yahoo.es","0005","Paris");
		Abonne a9 = new Abonne("Macron","Emmanuel","Elysée","0600000000","manumac@yahoo.es","0005","Paris");
		Abonne a10 = new Abonne("Macron","Emmanuel","Elysée","0600000000","manumac@yahoo.es","0005","Paris");
		
		
		Groupe tg = new Groupe(12.00);
		Jeune tj = new Jeune(25.00);
		Senior ts = new Senior(18.00);
		
		Cirque gc = new Cirque("Pinder");
		Danse gd = new Danse("Capuera");
		Marionnette gm = new Marionnette("Pinocchio");
		Musique gmm = new Musique("Shakira");
		Opera go = new Opera("Carmen");
		Theatre gt = new Theatre("La Cantatrice chauve");
		
		Spectacle s1 = new Spectacle("The Miles Show",120,480,"Theatre");
		Artiste art1 = new Artiste("Miles Mille",s1);
		Representation rep1 = new Representation("Lundi","12h30",false,s1);
		
		Spectacle s2 = new Spectacle("The Miles Show : REMAKE",120,480,"Musique");
		Artiste art2 = new Artiste("Miles Mille Jnr",s2);
		Representation rep2 = new Representation("Mardi","18h30",false,s2);

		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
		String dateInString1 ="7-Jun-2024";
		Date date1 = formatter.parse(dateInString1);
		
		String dateInString2 ="12-Jun-2024";
		Date date2 = formatter.parse(dateInString2);
		
		Reservation res1 = new Reservation("1000",dateInString1,dateInString2,a1,rep1);
		Reservation res2 = new Reservation("1002",dateInString1,dateInString2,a2,rep2);
		Reservation res3 = new Reservation("1002",dateInString1,dateInString2,a3,rep2);
		Reservation res4 = new Reservation("1003",dateInString1,dateInString2,a4,rep2);
		Reservation res5 = new Reservation("1004",dateInString1,dateInString2,a5,rep2);
		Reservation res6 = new Reservation("1005",dateInString1,dateInString2,a6,rep2);
		Reservation res7 = new Reservation("1006",dateInString1,dateInString2,a7,rep2);
		Reservation res8 = new Reservation("1007",dateInString1,dateInString2,a8,rep2);
		Reservation res9 = new Reservation("1008",dateInString1,dateInString2,a9,rep2);
		Reservation res10 = new Reservation("1009",dateInString1,dateInString2,a10,rep2);
		Reservation res11 = new Reservation("1010",dateInString1,dateInString2,a10,rep2);
		
		Zone z1 = new Zone("Orchestre",s1);
		Zone z2 = new Zone("Balcon",s1);
		Zone z3 = new Zone("Loges Gauches",s1);
		Zone z4 = new Zone("Loges Droites",s1);
		
		Zone z5 = new Zone("Orchestre",s2);
		Zone z6 = new Zone("Balcon",s2);
		Zone z7 = new Zone("Loges Gauches",s2);
		Zone z8 = new Zone("Loges Droites",s2);
		
		Fauteuil f1 = new Fauteuil("C","3",z1);
		Fauteuil f2 = new Fauteuil("A","12",z1);

		Billet b1 = new Billet("0001",tj,f1);
		Billet b2 = new Billet("0002",tj,f2);
		Billet b3 = new Billet("0003",tj,f1);
		Billet b4 = new Billet("0004",tj,f1);
		
		res1.ajouterRepresentation(rep1);
		res1.ajouterPlacement(b1);
		res1.ajouterPlacement(b2);
		res1.ajouterPlacement(b3);
		res1.ajouterPlacement(b4);
		res2.ajouterPlacement(b2);

		lesReservations.add(res1);
		lesReservations.add(res2);
		lesReservations.add(res2);
		lesReservations.add(res3); 
		lesReservations.add(res4); 
		lesReservations.add(res5);
		lesReservations.add(res6); 
		lesReservations.add(res7); 
		lesReservations.add(res8); 
		lesReservations.add(res9); 
		lesReservations.add(res10); 
		lesReservations.add(res11);
		
	}

	@Override
	public String toString() {
		return "Donnees [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]"+"test";
	}

	public static ObservableList<Reservation> getLesReservations() {
		return lesReservations;
	}
	
	

}
