package vue;

import java.text.ParseException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import controleur.Main;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import modele.Billet;
import modele.Donnees;
import modele.Fauteuil;
import modele.Representation;
import modele.Reservation;
import modele.Spectacle;
import modele.Client.Client;

public class CtrlPlanSalle {



    
    private TableColumn<Reservation, Integer> columnBillet = new TableColumn<Reservation, Integer>("Billet");

    
    private TableColumn<Reservation, String> columnClient = new TableColumn<Reservation, String>("Client");

  
    private TableColumn<Reservation, String> columnDate = new TableColumn<Reservation, String>("Date");

    
    private TableColumn<Reservation, String> columnDateConf = new TableColumn<Reservation, String>("ConfDate");

    
    private TableColumn<Reservation, String> columnNumResa = new TableColumn<Reservation, String>("NuméroRésa");

    
    private TableColumn<Reservation, String> columnRepre =new TableColumn<Reservation, String>("Représentation");
    
    @FXML
    private MenuItem balcon;

    @FXML
    private Button bnFermerPlan;

    @FXML
    private GridPane gridNomVille;

    @FXML
    private GridPane gridNumReserv;

    @FXML
    private GridPane gridPlaces;

    @FXML
    private Label laNom;

    @FXML
    private Label laNumReserv;

    @FXML
    private Label laPlaceLi;

    @FXML
    private Label laPlacePr;

    @FXML
    private Label laPlaceSe;

    @FXML
    private Label laRecherche;

    @FXML
    private Label laVille;

    @FXML
    private MenuItem logesDroites;

    @FXML
    private MenuItem logesGauche;

    @FXML
    private MenuButton menuZone;

    @FXML
    private MenuItem orchestre;

    @FXML
    private RadioButton rbNomVille;

    @FXML
    private RadioButton rbNumReserv;

    @FXML
    private TableView<Reservation> tableau;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfNumReserv;

    @FXML
    private TextField tfVille;

    @FXML
    private ToggleGroup tri;
    
    @FXML
    void clicFermerPlan(ActionEvent event) {
        controleur.Main.fermerPlan();
    }

    private HashMap<String, Integer>lesLettresOchestre = new HashMap<String, Integer>();
    
    private ArrayList<String>lesLettres = new ArrayList<String>();

    
    private ArrayList<ArrayList<Label>> labels;
    
    private int placesLibres = 0;

    @FXML
    void gererEndroit(ActionEvent event) {
    	labels = new ArrayList<ArrayList<Label>>();
    	
    	gridPlaces.getChildren().clear();
    	Object o = event.getSource();
    	if(o.equals(orchestre)) {
    		menuZone.setText(orchestre.getText());
    		for(int i = 1; i < 23; i++) {
    			ArrayList<Label> row = new ArrayList<Label>();
				int incremente = 0;
				for (int j = 0;j<15;j++) {
					incremente++;
					Label lb1 = new Label(incremente+"");
					row.add(lb1);
					lb1.getStyleClass().add("siege");
//						lb1.getStyleClass().add("siege_occupe");
					gridPlaces.add(lb1,i,j);
					lb1.setTooltip(new Tooltip("Siege :"+incremente+""));
					placesLibres++;
					
				}
				placesLibres++;
				labels.add(row);
			}
			for(int k = 1; k < lesLettres.size()+1;k ++) {
				int indice = lesLettres.size() - k;
				gridPlaces.add(new Label(lesLettres.get(indice)),k,15);
			}	
			
			laPlaceLi.setText(placesLibres - 22+"places libres");
			placesLibres = 0;
    		
    		
    	}else if(o.equals(balcon)) {
    		menuZone.setText(balcon.getText());
    		for(int valeur = 9; valeur <=14;valeur++) {
    			int incremente = 0;
    			for(int k = 6; k < 11;k++) {
    				incremente++;
    				Label lb1 = new Label(incremente+"");
    				lb1.getStyleClass().add("siege");
    				gridPlaces.add(lb1, valeur, k);
    				lb1.setTooltip(new Tooltip("Siege :"+incremente+""));
    				placesLibres++;
    			}
    			gridPlaces.add(new Label(lesLettres.get(5)), 9, 11);
    			gridPlaces.add(new Label(lesLettres.get(4)),10, 12);
    			gridPlaces.add(new Label(lesLettres.get(3)), 11, 13);
    			gridPlaces.add(new Label(lesLettres.get(2)), 12, 14);
    			gridPlaces.add(new Label(lesLettres.get(1)), 13, 15);
    			gridPlaces.add(new Label(lesLettres.get(0)), 14, 16);
    			valeur+=1;
    			incremente = 0;
    			for(int k = 5; k < 12;k++) {
    				incremente++;
    				Label lb1 = new Label(incremente+"");
    				lb1.getStyleClass().add("siege");
    				gridPlaces.add(lb1, valeur, k);
    				lb1.setTooltip(new Tooltip("Siege :"+incremente+""));
    				placesLibres += 1;
    			}
    			valeur+=1;
    			incremente =0;
    			for(int k = 4; k < 13;k++) {
    				incremente++;
    				Label lb1 = new Label(incremente+"");
    				lb1.getStyleClass().add("siege");
    				gridPlaces.add(lb1, valeur, k);
    				lb1.setTooltip(new Tooltip("Siege :"+incremente+""));
    				placesLibres += 1;
    			}
    			valeur+=1;
    			incremente = 0;
    			for(int k = 3; k < 14;k++) {
    				incremente++;
    				Label lb1 = new Label(incremente+"");
    				lb1.getStyleClass().add("siege");
    				gridPlaces.add(lb1, valeur, k);
    				lb1.setTooltip(new Tooltip("Siege :"+incremente+""));
    				placesLibres += 1;
    			}
    			valeur+=1;
    			incremente = 0;
    			for(int k = 2; k < 15;k++) {
    				Label lb1 = new Label(incremente+"");
    				lb1.getStyleClass().add("siege");
    				incremente++;
    				gridPlaces.add(lb1, valeur, k);
    				lb1.setTooltip(new Tooltip("Siege :"+incremente+""));
    				placesLibres += 1;
    			}
    			valeur+=1;
    			incremente = 0;
    			for(int k = 1; k < 16;k++) {
    				incremente++;
    				Label lb1 = new Label(incremente+"");
    				lb1.getStyleClass().add("siege");
    				gridPlaces.add(lb1, valeur, k);
    				lb1.setTooltip(new Tooltip("Siege :"+incremente+""));
    				placesLibres += 1;
    			}
    		}
    		laPlaceLi.setText(placesLibres+"places libres");
    		placesLibres = 0;
   
    	}else if(o.equals(logesDroites)) {
    		menuZone.setText(logesDroites.getText());
    		for(int j = 7;j < 10;j++) {
    			int incremente = 15;
    			for(int i = 4; i < 19; i++) {
    				Label lb1 = new Label(incremente+"");
    				lb1.getStyleClass().add("siege");
    				gridPlaces.add(lb1, i, j);
    				lb1.setTooltip(new Tooltip("Siege :"+incremente+""));
    				incremente--;
    				placesLibres++;
    			}
    		}
    		laPlaceLi.setText(placesLibres+"places libres");
    		placesLibres = 0;
    		gridPlaces.add(new Label(lesLettres.get(0)+""), 19, 7);
    		gridPlaces.add(new Label(lesLettres.get(1)+""), 19, 8);
    		gridPlaces.add(new Label(lesLettres.get(2)+""), 19, 9);
    	}else if(o.equals(logesGauche)) {
    		menuZone.setText(logesGauche.getText());
    		for(int j = 7;j < 10;j++) {
    			int incremente = 15;
    			for(int i = 4; i < 19; i++) {
    				Label lb1 = new Label(incremente+"");
    				lb1.getStyleClass().add("siege");
    				gridPlaces.add(lb1, i, j);
    				lb1.setTooltip(new Tooltip("Siege :"+incremente+""));
    				incremente--;
    				placesLibres++;
    			}
    		}
    		laPlaceLi.setText(placesLibres+"places libres");
    		placesLibres = 0;
    		gridPlaces.add(new Label(lesLettres.get(2)+""), 19, 7);
    		gridPlaces.add(new Label(lesLettres.get(1)+""), 19, 8);
    		gridPlaces.add(new Label(lesLettres.get(0)+""), 19, 9);
    	}
    }

    @FXML
    void selecNomVille(ActionEvent event) {
        gridNumReserv.setVisible(!gridNumReserv.isVisible());
        gridNomVille.setVisible(!gridNomVille.isVisible());
        gridNomVille.setVisible(gridNomVille.isVisible());

    }


    @FXML
    void selecNumRes(ActionEvent event) {
        gridNomVille.setVisible(!gridNomVille.isVisible());
        gridNumReserv.setVisible(!gridNumReserv.isVisible());
        gridNumReserv.setVisible(gridNumReserv.isVisible());
    }

    @FXML
    void menuChoix(ActionEvent event) {
    	gererEndroit(event);
    }

    @FXML void initialize() throws ParseException {
    	int incrementeLettre = 22;
    	for (int i = 65; i <= 86; i++) {
    	    lesLettres.add(String.valueOf((char) i));
    	    lesLettresOchestre.put(String.valueOf((char) i), incrementeLettre);
    	    incrementeLettre--;
    	}
    	rbNomVille.setSelected(true);
    	gridNumReserv.setVisible(!gridNumReserv.isVisible());
    	tfNom.setTooltip(new Tooltip("Maximum 15 caractères, uniquement des lettres"));
    	tfVille.setTooltip(new Tooltip("Maximum 15 caractères, uniquement des lettres"));
    	tfNumReserv.setTooltip(new Tooltip("Maximum 10 caractères, uniquement des chiffres"));
    	Donnees.init();
    	ObservableList<Reservation> reservations = Donnees.getLesReservations();
    	for (Reservation reservation : reservations) {
    	    System.out.println(reservation);
    	}
    	liaisonTable();
    	
    	FilteredList<Reservation> filteredData = new FilteredList<>(FXCollections.observableArrayList(Donnees.getLesReservations()), b -> true);

    	tfNumReserv.textProperty().addListener((observable, oldValue, newValue) -> {
    	    applyFilter(filteredData);
    	});

    	tfNom.textProperty().addListener((observable, oldValue, newValue) -> {
    	    applyFilter(filteredData);
    	});

    	tfVille.textProperty().addListener((observable, oldValue, newValue) -> {
    	    applyFilter(filteredData);
    	});

    	tableau.setItems(filteredData);
    	
    	recupFauteil();
    	}
    private void applyFilter(FilteredList<Reservation> filteredData) {
    	filteredData.setPredicate(reservation -> {
    		if ((tfNumReserv.getText() == null || tfNumReserv.getText().isEmpty()) &&
    				(tfNom.getText() == null || tfNom.getText().isEmpty()) &&
    				(tfVille.getText() == null || tfVille.getText().isEmpty())) {
    			return true;
    		}
    	
    		String filterNumRes = tfNumReserv.getText().toLowerCase();
    		if (!filterNumRes.isEmpty() && !reservation.getNumero().toLowerCase().contains(filterNumRes)) {
    			return false;
    		}

    		String filterNom = tfNom.getText().toLowerCase();
    		if (!filterNom.isEmpty() && !reservation.getClient().getNom().toLowerCase().contains(filterNom)) {
    			return false;
    		}
    		
    		String filterVille = tfVille.getText().toLowerCase();
    		if (!filterVille.isEmpty() && !reservation.getClient().getVille().toLowerCase().contains(filterVille)) { 
    			return false;
    		}
    		
    		return true;
    	});

    	
    	
    }
    
    
    private void liaisonTable() {
		columnNumResa.setCellValueFactory(new PropertyValueFactory<Reservation, String>("numero"));
		columnClient.setCellValueFactory(cellData -> {
			Reservation reservation = cellData.getValue();
			String nomClient = reservation.getClient().getNom() +" "+ reservation.getClient().getPrenom() +" "+ reservation.getClient().getNumero();
			return new SimpleStringProperty(nomClient);
		});
		columnDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
		columnDateConf.setCellValueFactory(cellData -> {
			String date = cellData.getValue().getConfDate();
			return new SimpleStringProperty(date);
		});
//		columnRepre.setCellValueFactory(new PropertyValueFactory<Reservation, ObservableList<Representation>>("mesRepresentations"));
		
		 columnBillet.setCellValueFactory(cellData -> {
	            Reservation reservation = cellData.getValue();
	            int nombreDeBillets = reservation.getMesBillets().size();
	            return new SimpleIntegerProperty(nombreDeBillets).asObject();
	        });
		 
		 columnRepre.setCellValueFactory(cellData -> {
			 String resa = "";
			 for(int i = 0; i < cellData.getValue().getMesRepresentations().size();i++) {
				 resa += cellData.getValue().getMesRepresentations().get(i).getSpec();
			 }
			 return new SimpleStringProperty(resa);
		 });
		tableau.getColumns().addAll(columnNumResa,columnClient,columnBillet,columnDate,columnDateConf,columnRepre);
		tableau.setItems(Donnees.getLesReservations());
		tableau.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reservation>() {
			@Override
			public void changed(ObservableValue<? extends Reservation> arg0, Reservation arg1,Reservation arg2) {
				ArrayList<Billet>lesBillet = new ArrayList<Billet>();
				
//				String letters = "abcdefghijklmnopqrstuv";
//				
//				for (Billet billet : arg2.getMesBillets()) {
//					Fauteuil f = billet.getMonFauteuil();
//					int indice_lettre = 0;
//					
//					for (int i = 0; i < letters.length(); i++) {
//						char l = letters.charAt(i);
//						
//						if (String.valueOf(l).equals(f.getRangee())) {
//							indice_lettre = i;
//							break;
//						}
//					}
//										
//					Label label = labels.get(letters.length() - indice_lettre).get(Integer.parseInt(f.getNumero()));
//					label.getStyleClass().add("siege_occupe");
//					
//				}
				
				
				for(Billet bille:lesBillet) {
					bille.affiche();
				}
				String billetsPris="";
				int nbPlaces = 0;
				
				for(Billet bille : arg2.getMesBillets()) {
					lesBillet.add(bille);
					billetsPris += bille.getMonFauteuil().getNumero()+bille.getMonFauteuil().getRangee();
					nbPlaces++;
				}
				
				laPlacePr.setText("Les places libres:"+billetsPris);
				
//				int nbPlacesLibres = 0;
//				nbPlacesLibres = Integer.parseInt(laPlaceLi.getText());
//				nbPlacesLibres -= arg2.getMesBillets().size();
//				
//				System.out.println(nbPlacesLibres);
//				
//				laPlacePr.setText(nbPlacesLibres+"");
				
				
				affichePlaceReservation(lesBillet); 
				Main.clic(arg2.getNumero(), arg2.getClient().getPrenom(),arg2.getClient().getNom(), arg2.getClient().getNumero(),lesBillet,arg2.getDate(),arg2.getConfDate(),arg2.getMesRepresentations().get(0).getHeure());
				
			}
		});
	
		
	}

	@FXML
    void gererErreur(KeyEvent event) {
    	if (tfVille.getText().length() >= 16){
    		tfVille.deletePreviousChar();
    	}
    	if (tfNom.getText().length() >= 16){
    		tfNom.deletePreviousChar();
    	}
    	if (tfNumReserv.getText().length() >= 11){
    		tfNumReserv.deletePreviousChar();
    	}
        tfNom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[^0-9]*")) {
                tfNom.setText(newValue.replaceAll("[0-9]", ""));
            }
        });
        tfVille.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[^0-9]*")) {
                tfVille.setText(newValue.replaceAll("[0-9]", ""));
            }
        });
        tfNumReserv.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            	tfNumReserv.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
	}
        
    	
	
	public void recupFauteil() {
		HashMap<Map.Entry<String, String>, Boolean>listeFauteils = new HashMap<Map.Entry<String,String>, Boolean>();
		for(int i = 0;i < Donnees.getLesReservations().size();i++) {
			Reservation reservation = Donnees.getLesReservations().get(i);
			for(Billet bi : reservation.getMesBillets()) {
				listeFauteils.put(Map.entry(bi.getMonFauteuil().getNumero(), bi.getMonFauteuil().getNumero()),true);
			}
		}
	}
	
	public void affichePlaceReservation(ArrayList<Billet>lesBillets) {
		int rangee = 0;
		HashMap<String, String>lesReserve = new HashMap<String, String>();
		for(Billet billet : lesBillets) {
			lesReserve.put(billet.getMonFauteuil().getRangee(), billet.getMonFauteuil().getNumero());
		}
		for(Map.Entry<String, String>entry : lesReserve.entrySet()) {
			System.out.println(entry.getKey()+entry.getValue());
			for(Map.Entry<String, Integer>entry2 : lesLettresOchestre.entrySet()) {
				if(entry2.getKey().equals(entry.getKey())) {
					rangee = entry2.getValue();
				}
			}
		}
		
		System.out.println(rangee);
		
	}	
	
	
    	
}
