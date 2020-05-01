package ui.addperson;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.sql.DatabaseMetaData;
import java.util.ResourceBundle;

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

    DatabaseHandler databaseHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHandler = new DatabaseHandler();
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        String personFirstName = firstName.getText();
        String personSecondName = secondName.getText();
        String personMiddleName = middleName.getText();
        String personID = ID.getText();

        if (personID.isEmpty() || personFirstName.isEmpty() ||
                personSecondName.isEmpty() || personMiddleName.isEmpty()){
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
        if (databaseHandler.execAction(query)){
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
    }
}
