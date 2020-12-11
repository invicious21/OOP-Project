package com.oktayosman;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private ImageView adminImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Button createOrganizerButton;
    @FXML
    private Button createDistributorButton;
    @FXML
    private Button createEventButton;
    @FXML
    private Button profilesButton;
    @FXML
    private Button distributorRatingButton;




    public void initialize(URL url, ResourceBundle resourceBundle) {
        File adminFile = new File("admin.png");
        Image adminImage = new Image(adminFile.toURI().toString());
        adminImageView.setImage(adminImage);
    }

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
