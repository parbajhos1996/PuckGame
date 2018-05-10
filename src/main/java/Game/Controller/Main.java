package Game.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A programot elindító osztály.
 */
public class Main extends Application
{
    /**
     * A logger.
     */
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * A program indításakor látható felhasználói
     * felületet indítja el.
     * @param primaryStage Az elsődleges stage.
     * @throws IOException A fájl nem megfelelő.
     */
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Parent root = FXMLLoader.load(ClassLoader.class.getResource("/View/first.fxml"));
        primaryStage.setTitle("Puck Game");
        primaryStage.setScene(new Scene(root, 1000, 720));
        primaryStage.show();
        logger.info("Első ablak létrehozva.");
    }

    /**
     * A program belépési pontja.
     * @param args Parancssori argumentum
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
