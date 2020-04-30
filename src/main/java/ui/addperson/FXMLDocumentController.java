package ui.addperson;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
    }
}
