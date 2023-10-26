package com.mycompany.javafx_db_example;

import java.io.File;

import com.mycompany.javafx_db_example.db.ConnDbOps;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class DB_GUI_Controller implements Initializable {
    //Creates list for data in Person class to be stored
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    // Creates instance of ConnDbOps class in the controller
    private ConnDbOps dbOps = new ConnDbOps();

    @FXML
    TextField name, email, phone, address, password;
    @FXML
    private TableView<Person> tv;
    @FXML
    private TableColumn<Person, String> tv_name, tv_email, tv_phone, tv_address, tv_password;

    @FXML
    ImageView img_view;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize your ConnDbOps object
        ConnDbOps dbOps = new ConnDbOps();

        // Fetch data from the database using ConnDbOps
        ObservableList<Person> databaseData = dbOps.fetchUserData();

        // Bind the data to the TableView
        tv_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tv_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tv_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tv_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tv_password.setCellValueFactory(new PropertyValueFactory<>("password"));


        tv.setItems(databaseData);
    }

    //  Must exit application to see the added record, looking for fix...
    @FXML
    protected void addNewRecord() {
        // Create a new Person object from the form data
        Person newPerson = new Person(
                name.getText(),
                email.getText(),
                phone.getText(),
                address.getText(),
                password.getText()
        );

        // Insert the new record into the database
        dbOps.insertUser(newPerson);

        // Add the new record to the data list
        data.add(newPerson);

        // Clear the form and refresh the TableView
        clearForm();
    }
    // Clears data from all text fields
    @FXML
    protected void clearForm() {
        name.clear();
        email.setText("");
        phone.setText("");
        address.setText("");
        password.setText("");
    }

    /*  @FXML
    private void initialize() {
        // Create a KeyCombination for Ctrl + F
        KeyCombination ctrlF = new KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN);

        // Set the accelerator for the "close" menu item
    }
    */

    @FXML
    protected void closeApplication() {
        System.exit(0);
    }

    /* editRecord() has an issue that after a record is edited successfully,
     the program has an index out of bounds error. Functionality still exists regardless.
     */
    @FXML
    protected void editRecord() {
        // Get the selected person
        Person selectedPerson = tv.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            // Create a new Person object with updated data
            Person updatedPerson = new Person(
                    name.getText(),
                    email.getText(),
                    phone.getText(),
                    address.getText(),
                    password.getText()
            );

            // Update the record in the database
            dbOps.updateUser(selectedPerson.getEmail(), updatedPerson);

            // Update the record in the data list
            int index = data.indexOf(selectedPerson);

            if (index != -1) {
                data.set(index, updatedPerson);

                // Clear the form
                clearForm();
            } else {
                // Handle the case where the selected item is not found in the data list
                System.out.println("Selected user not found in the data list.");
            }
        } else {
            // Handle the case where no item is selected
            System.out.println("Please select a user to edit.");
        }
    }




    @FXML
    protected void deleteRecord() {
        // Get the selected person
        Person selectedPerson = tv.getSelectionModel().getSelectedItem();

        // Delete the record from the database
        dbOps.deleteUser(selectedPerson.getEmail());

        // Remove the record from the data list
        data.remove(selectedPerson);
    }




    @FXML
    protected void showImage() {
        // Loads image to the image view
        File file= (new FileChooser()).showOpenDialog(img_view.getScene().getWindow());
        if(file!=null){
            img_view.setImage(new Image(file.toURI().toString()));

        }
    }





    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        // Gets the records of the selected person and puts them in text fields
        Person p= tv.getSelectionModel().getSelectedItem();
        name.setText(p.getName());
        email.setText(p.getEmail());
        phone.setText(p.getPhone());
        address.setText(p.getAddress());
        password.setText(p.getPassword());


    }
}
