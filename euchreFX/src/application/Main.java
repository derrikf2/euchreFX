package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * <h1> Main </h1>
 * <p> The main class's only purpose, as an extension 
 * of Application, is essentially is to load the JavaFX UI,
 * and set the "Game Play Scene".
 * @author Derrik Fleming
 * @author Josh T
 * @author Jarret Swales
 * @version 1.0
 * @since 2016 Fall
 */

public class Main extends Application {
	/** This is the GUI stage. */
	private Stage stage;
	
	/** This is the main BorderPan that the gameplay scene
	 * is added to. */
	private BorderPane mainPane;
	
	
	/**
	* Standard method call to start JavaFX UI.
    * 
    * @param args Default.
    */
    public static void main(final String[] args) {
        launch(args);

    }
    
    @Override
	public final void start(final Stage s) throws Exception {
        try {
            // StackPane root = new StackPane();
        	this.stage = s;
        	this.stage.setTitle("EuchRetro");
        	// load scene
            showSceneView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method sets the stage to the main scene (ie 
     * really just the background).
     * 
     * @throws IOException If loading game play scene fails.
     */
    public final void showSceneView() throws IOException {
		try {	
    		// loading scene.fxml
			FXMLLoader loader = new FXMLLoader();
			//macOS path syntax
			loader.setLocation(
					Main.class.getResource("/application/view/SceneView.fxml"));
			// set mainPane to loaded file: scene.fxml
			mainPane = loader.load();
			// Setting scene on stage
			Scene scene = new Scene(mainPane);
			stage.setScene(scene);
			//UI style sheet
			scene.getStylesheets().add("/application/view/Styles.css");
			stage.show();
		} catch (IOException e) {
			System.out.println("The game play scene could not be found.");
		}
    }
    
    /**
     * The following methods are being reserved for Release 2.
     */
    
//    /**
//     * Future method.
//     */
//    public final void showPreGameItems() {
//    	// Prompt user for player names, including ai
//    	// Select difficulty level
//    	// Play with a friend? if we figure out game server
//    	// Only displays on start, or unless accessed via file menu
//    	FXMLLoader loader = new FXMLLoader();
//
//    	loader.setLocation(
//    			Main.class.getResource("/application/view/PreGameItems.fxml"));
//    	
//    	BorderPane preGamePlayItems = loader.load();
//    	mainPane.setCenter(preGamePlayItems);
//    	
//    }
//    
//    /**
//     * Future method.
//     * @throws IOException.
//     */
//    public void showPostGameWin() {
//    	
//    }
//    
//    /**
//     * Future method.
//     * @throws IOException.
//     */
//    public void showPostGameLoss() {
//    	
//    }
}