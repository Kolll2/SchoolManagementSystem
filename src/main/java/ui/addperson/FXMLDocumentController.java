package ui.addperson;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FXMLDocumentController {
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
    public void save(ActionEvent actionEvent) {
    }


    @FXML
    public void cancel(ActionEvent actionEvent) {
    }
}
