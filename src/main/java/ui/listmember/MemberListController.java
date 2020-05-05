package ui.listmember;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.addperson.FXMLDocumentController;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberListController implements Initializable {

    private ObservableList<Member> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Member> tableView;

    @FXML
    private TableColumn<Member, String> nameCol;

    @FXML
    private TableColumn<Member, String> memberIDCol;

    @FXML
    private TableColumn<Member, String> mobileCol;

    @FXML
    private TableColumn<Member, String> emailCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }

    private void initCol(){
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        memberIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String query = "SELECT * FROM MEMBER ";
        ResultSet resultSet = databaseHandler.execQuery(query);
        try {
            while (resultSet.next()) {
                String n = resultSet.getString("name");
                String mID = resultSet.getString("id");
                String mle = resultSet.getString("mobile");
                String em = resultSet.getString("email");

                list.add(new Member(n, mID, mle, em));

                System.out.println(n + " \t " + em + " \t " + mle);
            }
        } catch (SQLException e) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null,e);
        }

        tableView.getItems().setAll(list);

    }

    public static class Member {
        private final SimpleStringProperty name;
        private final SimpleStringProperty memberID;
        private final SimpleStringProperty mobile;
        private final SimpleStringProperty email;

        public Member(String name, String memberID, String mobile, String email) {
            this.name = new SimpleStringProperty(name);
            this.memberID = new SimpleStringProperty(memberID);
            this.mobile = new SimpleStringProperty(mobile);
            this.email = new SimpleStringProperty(email);
        }



        public String getName() {
            return name.get();
        }

        public String getMemberID() {
            return memberID.get();
        }

        public String getMobile() {
            return mobile.get();
        }

        public String getEmail() {
            return email.get();
        }
    }
}
