package ui.listperson;

import database.DatabaseHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ui.addperson.FXMLDocumentController;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonListController implements Initializable {

    private ObservableList<Person> list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, Long> dbID_col;

    @FXML
    private TableColumn<Person, String> firstName_col;

    @FXML
    private TableColumn<Person, String> secondName_col;

    @FXML
    private TableColumn<Person, String> middleName_col;

    @FXML
    private TableColumn<Person, Long> innerID_col;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intiCol();
        loadData();
    }

    private void loadData() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String query = "SELECT * FROM PERSON ";
        ResultSet resultSet = databaseHandler.execQuery(query);
        try {
            while (resultSet.next()) {
                String fn = resultSet.getString("firstName");
                String sn = resultSet.getString("secondName");
                String mn = resultSet.getString("middleName");
                Long id = resultSet.getLong("id");

                list.add(new Person(fn,sn,mn,id));

                System.out.println(fn);
            }
        } catch (SQLException e) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null,e);
        }

        tableView.getItems().setAll(list);

    }

    private void intiCol() {
        dbID_col.setCellValueFactory(new PropertyValueFactory<>("dbID"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secondName_col.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        middleName_col.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        innerID_col.setCellValueFactory(new PropertyValueFactory<>("innerID"));

    }

    public class Person {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty secondName;
        private final SimpleStringProperty middleName;
        private final SimpleLongProperty dbID;
        private final SimpleLongProperty innerID;

        public Person(Long dbID, String firstName, String  secondName, String  middleName, Long innerID) {
            this.firstName = new SimpleStringProperty(firstName);
            this.secondName = new SimpleStringProperty(secondName);
            this.middleName = new SimpleStringProperty(middleName);
            this.dbID = new SimpleLongProperty(dbID);
            this.innerID = new SimpleLongProperty(innerID);
        }

        public Person(String firstName, String  secondName, String  middleName, Long innerID) {
           this(0L, firstName,secondName,middleName, innerID);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public SimpleStringProperty firstNameProperty() {
            return firstName;
        }

        public String getSecondName() {
            return secondName.get();
        }

        public SimpleStringProperty secondNameProperty() {
            return secondName;
        }

        public String getMiddleName() {
            return middleName.get();
        }

        public SimpleStringProperty middleNameProperty() {
            return middleName;
        }

        public long getDbID() {
            return dbID.get();
        }

        public SimpleLongProperty dbIDProperty() {
            return dbID;
        }

        public long getInnerID() {
            return innerID.get();
        }

        public SimpleLongProperty innerIDProperty() {
            return innerID;
        }
    }
}
