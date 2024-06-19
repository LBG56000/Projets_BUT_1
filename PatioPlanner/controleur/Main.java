package controleur;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import modele.Billet;
import vue.FenBillets;
import vue.FenInfosResa;
import vue.FenPlanSalle;

public class Main extends Application{
	
	private static FenPlanSalle f;
	private static FenInfosResa f1;
	private static FenBillets f2;
	
	public void start(Stage primaryStage) throws Exception {
		f = new FenPlanSalle(); 
		f.show();
		f1 = new FenInfosResa();
//		f1.show();
		f2 = new FenBillets();
//		f2.show();
	}
	
	public static void main(String[] args) {
		Application.launch();
	}
	
	public static void clic(String numCli,String prenom,String nom,String numReser,ArrayList<Billet>lesBillets,String date,String dateConf, String heure) {
		f1.clicInfo(numCli, prenom,nom, numReser,lesBillets,date,dateConf,heure);
		f1.show();
	}
	
	
	 public static void fermerPlan() {
	        System.exit(0);
	    }

	 public static void fermerInfos() {
	        f1.close();
	 }

	 public static void fermerBillets() {
	        f2.close();
	 }

	public static void clicBillet(ArrayList<Billet> lesBilletsTemp) {
		f2.clicBillet(lesBilletsTemp);
		f2.show();
		
	}
	
	
}
	
	

