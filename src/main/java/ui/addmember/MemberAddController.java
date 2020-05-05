package ui.addmember;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberAddController implements Initializable {

    DatabaseHandler databaseHandler;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField mobile;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton cancel;

    @FXML
    public AnchorPane rootPane;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addMember(ActionEvent event) {
        String memberName = name.getText();
        String memberID = id.getText();
        String memberMobile = mobile.getText();
        String memberEmail = email.getText();

        if (memberName.isEmpty() || memberID.isEmpty() ||
                memberMobile.isEmpty() || memberEmail.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }

        String query = "INSERT INTO MEMBER VALUES ("
                + "'" + memberName + "',"
                + "'" + memberID + "',"
                + "'" + memberMobile + "',"
                + "'" + memberEmail + "')";

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHandler = new DatabaseHandler();
        checkData();
    }

    private void checkData() {
        String query = "SELECT name FROM MEMBER ";
        ResultSet resultSet = databaseHandler.execQuery(query);
        try {
            while (resultSet.next()) {
                String n = resultSet.getString("name");
                System.out.println(n);
            }
        } catch (SQLException e) {
            Logger.getLogger(MemberAddController.class.getName()).log(Level.SEVERE, null,e);
        }
    }
}
