package vue;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class FenPlanSalle extends Stage {
	CtrlPlanSalle ctrl;
	public FenPlanSalle() {
		this.setTitle("Patio'Planner : Plan de la salle");
		Scene laScene;
		try {
			laScene = new Scene(creerSceneGraph());
			this.setScene(laScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 this.setMinHeight(400);
	     this.setMinWidth(800);
	     this.setResizable(true);
		
	}

	private SplitPane creerSceneGraph() throws IOException {
     	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/PlanSalle.fxml"));
//        loader.load(getClass().getResource("/styleSheet.css"));
        SplitPane root = loader.load();
        root.getStylesheets().add(getClass().getResource("/styleSheet.css").toExternalForm());
        ctrl = loader.getController();
     	return root;
	}
}