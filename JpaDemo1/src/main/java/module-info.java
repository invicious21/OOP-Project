module JpaDemo1 {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.java;
    requires java.persistence;

    opens com.oktayosman;
}