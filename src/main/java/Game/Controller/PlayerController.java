package Game.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerController
{
    private static Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @FXML
    TextField textField1;
    @FXML
    TextField textField2;

    public static String player1;
    public static String player2;

    @FXML
    private void initialize()
    {
        logger.info("Player ablak megnyitva.");
        textField1.setText("Player 1");
        logger.info("Textfield1 beállítva.");
        textField2.setText("Player 2");
        logger.info("Textfield2 beállítva.");
    }

    @FXML
    private void buttonAction1(ActionEvent event)
    {
        logger.info("Gombra kattintás történt(PlayerController.Button1).");
        player1 = textField1.getText();
        player2 = textField2.getText();

        try
        {
            if (!textField1.getText().equals("") && !textField2.getText().equals("") &&
                    textField1.getText().length() < 12 && textField2.getText().length() < 12)
            {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(ClassLoader.class.getResource("/View/main.fxml"));
                stage.setScene(new Scene(root, 1000, 720));
                logger.info("Main ablakra váltás.");
            }
        } catch (IOException e)
        {
            logger.error(e.toString());
        }
    }

    @FXML
    private void buttonAction2(ActionEvent event) throws IOException
    {
        logger.info("Gombra kattintás történt(PlayerController.Button2).");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(ClassLoader.class.getResource("/View/first.fxml"));
        stage.setScene(new Scene(root, 1000, 720));
        logger.info("First ablakra váltás.");
    }
}
