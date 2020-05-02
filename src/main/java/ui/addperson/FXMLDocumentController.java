package ui.addperson;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLDocumentController implements Initializable {
    @FXML
    public JFXTextField firstName;
    @FXML
    public JFXTextField secondName;
    @FXML
    public JFXTextField middleName;
    @FXML
    public JFXTextField ID;
    @FXML
    public JFXButton save;
    @FXML
    public JFXButton cancel;
    @FXML
    public AnchorPane rootPane;

    DatabaseHandler databaseHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHandler = new DatabaseHandler();
        checkData();
    }

    private void checkData() {
        String query = "SELECT firstName FROM PERSON ";
        ResultSet resultSet = databaseHandler.execQuery(query);
        try {
            while (resultSet.next()) {
                String fn = resultSet.getString("firstName");
                System.out.println(fn);
            }
        } catch (SQLException e) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null,e);
        }
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        String personFirstName = firstName.getText();
        String personSecondName = secondName.getText();
        String personMiddleName = middleName.getText();
        String personID = ID.getText();

        if (personID.isEmpty() || personFirstName.isEmpty() ||
                personSecondName.isEmpty() || personMiddleName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }

        String query = "INSERT INTO PERSON VALUES ("
                + "'" + personID + "',"
                + "'" + personFirstName + "',"
                + "'" + personSecondName + "',"
                + "'" + personMiddleName + "')";
        System.out.println(query);
        if (databaseHandler.execAction(query)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed " + databaseHandler.getLastException());
            alert.showAndWait();
            return;
        }
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
