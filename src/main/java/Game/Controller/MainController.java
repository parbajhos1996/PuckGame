package Game.Controller;

import Game.Model.Jatek;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static Game.Controller.PlayerController.player1;
import static Game.Controller.PlayerController.player2;

public class MainController
{
    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;

    private Jatek jatek;

    @FXML
    private void initialize()
    {
        jatek = new Jatek(player1, player2);
        label1.setText(player1);
        label2.setText("3x " + jatek.getJatekos1().getKorong1().getSzin() + "(Left Mouse)");
        label3.setText("3x " + jatek.getJatekos1().getKorong2().getSzin() + "(Right Mouse)");
        logger.info("Az label első játékos nevére beállítva.");
    }

    private void hatter(int mezoSzam, String szin)
    {
        if (jatek.kovLepes(mezoSzam, szin))
        {
            String imgURL = "/View/" + szin + ".jpg";
            BackgroundImage image = new BackgroundImage(
                    new Image(getClass().getResourceAsStream(imgURL)),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            ((TilePane) anchorPane.getChildren().get(mezoSzam - 1)).setBackground(new Background(image));
            logger.info("A(z) " + Integer.toString(mezoSzam) + ". mező háttere beállítva.");
        }
    }

    private void jatekVege(MouseEvent event)
    {
        try
        {
            if (!jatek.getGyoztesStr().equals(""))
            {
                logger.info("A játék véget ért.");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, jatek.getGyoztesStr(), ButtonType.YES);
                alert.setTitle("Game over.");
                alert.setHeaderText("Game over.");
                alert.showAndWait();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/View/main.fxml"));
                stage.setScene(new Scene(root, 1000, 720));
                logger.info("Új játék elkezdődött.");
            }
        } catch (IOException e)
        {
            logger.error(e.toString());
        }
    }

    private void nevCsere()
    {
        if (jatek.getLepesSzam() % 2 == 0)
        {
            label1.setText(jatek.getJatekos1().getName());
            label2.setText(jatek.getJatekos1().getKorong1().getMennyiseg() + "x " + jatek.getJatekos1().getKorong1().getSzin() + "(Left Mouse)");
            label3.setText(jatek.getJatekos1().getKorong2().getMennyiseg() + "x " + jatek.getJatekos1().getKorong2().getSzin() + "(Right Mouse)");
        } else
        {
            label1.setText(jatek.getJatekos2().getName());
            label2.setText(jatek.getJatekos2().getKorong1().getMennyiseg() + "x " + jatek.getJatekos2().getKorong1().getSzin() + "(Left Mouse)");
            label3.setText(jatek.getJatekos2().getKorong2().getMennyiseg() + "x " + jatek.getJatekos2().getKorong2().getSzin() + "(Right Mouse");
        }
        logger.info("Név és korongcsere megtörtént.");
    }

    private String szinBe(MouseEvent event)
    {
        String szin = new String();
        if (event.getButton() == MouseButton.PRIMARY && jatek.getLepesSzam() % 2 == 0)
            szin = "blue";
        else if (event.getButton() == MouseButton.SECONDARY && jatek.getLepesSzam() % 2 == 0)
            szin = "red";
        else if (event.getButton() == MouseButton.PRIMARY && jatek.getLepesSzam() % 2 == 1)
            szin = "green";
        else if (event.getButton() == MouseButton.SECONDARY && jatek.getLepesSzam() % 2 == 1)
            szin = "yellow";

        logger.info("Szín egérkattintásnak megfelelően beállítva.");
        return szin;
    }

    @FXML
    private void addGridEvent1(MouseEvent event) throws IOException
    {
        String paneId = event.getPickResult().getIntersectedNode().getId();
        String[] parts = paneId.split("e");
        String szin = szinBe(event);
        hatter(Integer.parseInt(parts[1]), szin);
        jatekVege(event);
        nevCsere();
        logger.info(parts[1] + ". mezőre kattintás történt.");
    }

    @FXML
    private void buttonAction1(ActionEvent event)
    {
        logger.info("Gombra kattintás történt(MainController.Button1).");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        logger.info("Kilépés a programból.");
    }

    @FXML
    private void buttonAction2(ActionEvent event) throws IOException
    {
        logger.info("Gombra kattintás történt(MainController.Button2).");
        try
        {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(ClassLoader.class.getResource("/View/player.fxml"));
            stage.setScene(new Scene(root, 1000, 720));
        } catch (IOException e)
        {
            logger.error(e.toString());
        }
        logger.info("Player ablakra váltás.");
    }
}
