package Game.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstController
{
    private static Logger logger = LoggerFactory.getLogger(FirstController.class);

    @FXML
    private void buttonAction1(ActionEvent event)
    {
        logger.info("Gombra kattintás történt(FirstController.Button1).");
        try
        {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(ClassLoader.class.getResource("/View/player.fxml"));
            stage.setScene(new Scene(root, 1000, 720));
            logger.info("Player ablakra váltás.");
        } catch (IOException e)
        {
            logger.error(e.toString());
        }
    }

    @FXML
    private void buttonAction2(ActionEvent event)
    {
        logger.info("Gombra kattintás történt(FirstController.Button1).");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        logger.info("Kilépés a programból.");
    }
}
