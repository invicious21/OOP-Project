package com.oktayosman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.sql.Connection;

public class CreateOrganizerController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField honorariumTextField;
    @FXML
    private Button promoteButton;
    @FXML
    private Button backButton;

    public void promoteButtonOnAction(ActionEvent event) {

    }

    public void promoteClient() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


       // String getUser = "SELECT "

    }

    public void backButtonOnAction() {

    }


}
