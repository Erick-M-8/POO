package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    @FXML
    ListView<String> listalibros;
    @FXML
    ListView<String> listalibros2;
    @FXML
    TextField txt;

    @FXML
    private void button1_click(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage =  new Stage();
        stage.setTitle("Nuevo libro");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.showAndWait();
         // ------------------------Conexión con base de datos-------------------//

         Connection cnx = null;
         String base = "libreria";
         String svr = "localhost";
         String usr = "coevaluacion6";
         String pass = "coevaluacion6";
         String cnxstr = "jdbc:mysql://" + svr + "/" + base + "?useSSL=false";
         String drvclass = "com.mysql.cj.jdbc.Driver";
 
         try {
             // Intentar conectar base de datos
             Class.forName(drvclass);
             // Conexiòn establecida
             cnx = DriverManager.getConnection(cnxstr, usr, pass);
             System.out.println("Conexiòn establecida con base de datos");
         }
 
         catch (Exception excepcion) { // Si se produce una excepciòn
             System.out.println("Error: " + excepcion.getMessage());
             return;
         }
 
         ArrayList<String> lstforbook = new ArrayList<String>(); // Para llenar la lista de libros
 
         // Seleccionar datos
         try {
             String query = "Select titulo from catalogo;";
             Statement cmd = cnx.createStatement();
             ResultSet res = cmd.executeQuery(query);
             while (res.next()) {
                 lstforbook.add(res.getString("titulo"));
             }
             res.close();
             cmd.close();
             cnx.close();
         } catch (Exception excepcion) { // Si se produce una excepciòn
             System.out.println("Error: " + excepcion.getMessage());
             return;
         }
         listalibros.getItems().clear();
         listalibros.getItems().addAll(lstforbook); // Agregamos los resultados de la selecciòn
    }
    
    String cosa;
    @FXML
    public void button2_click() {
         // ------------------------Conexión con base de datos-------------------//
         Connection cnx = null;
         String base = "libreria";
         String svr = "localhost";
         String usr = "coevaluacion6";
         String pass = "coevaluacion6";
         String cnxstr = "jdbc:mysql://" + svr + "/" + base + "?useSSL=false";
         String drvclass = "com.mysql.cj.jdbc.Driver";
 
         try {
             // Intentar conectar base de datos
             Class.forName(drvclass);
             // Conexiòn establecida
             cnx = DriverManager.getConnection(cnxstr, usr, pass);
             System.out.println("Conexiòn establecida con base de datos");
         } catch (Exception excepcion) { // Si se produce una excepciòn
             System.out.println("Error: " + excepcion.getMessage());
             return;
         }
   
         // Insercion de datos
         try {
             String query = "delete from catalogo where titulo = '"+cosa+"'; ";
             System.out.println(""+cosa+"");
             Statement stmt = cnx.createStatement();
             stmt.execute(query);
             if(cosa != null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Mensaje del Sistema");
                alert.setHeaderText(null);
                alert.setContentText("El libro se elimino correctamente");
                alert.showAndWait();
             }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Mensaje del Sistema");
                alert.setHeaderText(null);
                alert.setContentText("Seleccione un elemento de la lista");
                alert.showAndWait();
             }
         }catch (Exception excepcion) { // Si se produce una excepciòn
             System.out.println("Error: " + excepcion.getMessage());
             return;
         }

         ArrayList<String> lstforbook = new ArrayList<String>(); // Para llenar la lista de libros

         // Seleccionar datos
         try {
             String query = "Select titulo from catalogo;";
             Statement cmd = cnx.createStatement();
             ResultSet res = cmd.executeQuery(query);
             while (res.next()) {
                 lstforbook.add(res.getString("titulo"));
             }
             res.close();
             cmd.close();
             cnx.close();
         } catch (Exception excepcion) { // Si se produce una excepciòn
             System.out.println("Error: " + excepcion.getMessage());
             return;
         }
         listalibros.getItems().clear();
         listalibros.getItems().addAll(lstforbook); // Agregamos los resultados de la selecciòn
    }
    
    @FXML
    public void txt_change() {
        // ------------------------Conexión con base de datos-------------------//
        Connection cnx = null;
        String base = "libreria";
        String svr = "localhost";
        String usr = "coevaluacion6";
        String pass = "coevaluacion6";
        String cnxstr = "jdbc:mysql://" + svr + "/" + base + "?useSSL=false";
        String drvclass = "com.mysql.cj.jdbc.Driver";

        try {
            // Intentar conectar base de datos
            Class.forName(drvclass);
            // Conexiòn establecida
            cnx = DriverManager.getConnection(cnxstr, usr, pass);
            System.out.println("Conexiòn establecida con base de datos");
        }

        catch (Exception excepcion) { // Si se produce una excepciòn
            System.out.println("Error: " + excepcion.getMessage());
            return;
        }

        ArrayList<String> booksearch = new ArrayList<String>();
        String buscadoString = txt.getText();

        // Seleccionar datos
        try {
            String query = "Select titulo from catalogo where titulo like '%" + buscadoString + "%';";
            Statement cmd = cnx.createStatement();
            ResultSet res = cmd.executeQuery(query);
            while (res.next()) {
                booksearch.add(res.getString("titulo"));
            }
            res.close();
            cmd.close();
            cnx.close();
        } catch (Exception excepcion) { // Si se produce una excepciòn
            System.out.println("Error: " + excepcion.getMessage());
            return;
        }
        listalibros2.getItems().clear();
        listalibros2.getItems().addAll(booksearch); // Agregamos los resultados de la selecciòn
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        //Inicializa componentes
        this.listalibros.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() { //Recuperar de lista
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
               basura(newValue); //Valor seleccionado en la lista
            }

            private void basura(String newValue) {
                cosa=newValue;
            }
        });

        // ------------------------Conexión con base de datos-------------------//

        Connection cnx = null;
        String base = "libreria";
        String svr = "localhost";
        String usr = "coevaluacion6";
        String pass = "coevaluacion6";
        String cnxstr = "jdbc:mysql://" + svr + "/" + base + "?useSSL=false";
        String drvclass = "com.mysql.cj.jdbc.Driver";

        try {
            // Intentar conectar base de datos
            Class.forName(drvclass);
            // Conexiòn establecida
            cnx = DriverManager.getConnection(cnxstr, usr, pass);
            System.out.println("Conexiòn establecida con base de datos");
        }

        catch (Exception excepcion) { // Si se produce una excepciòn
            System.out.println("Error: " + excepcion.getMessage());
            return;
        }

        ArrayList<String> lstforbook = new ArrayList<String>(); // Para llenar la lista de libros

        // Seleccionar datos
        try {
            String query = "Select titulo from catalogo;";
            Statement cmd = cnx.createStatement();
            ResultSet res = cmd.executeQuery(query);
            while (res.next()) {
                lstforbook.add(res.getString("titulo"));
            }
            res.close();
            cmd.close();
            cnx.close();
        } catch (Exception excepcion) { // Si se produce una excepciòn
            System.out.println("Error: " + excepcion.getMessage());
            return;
        }
        listalibros.getItems().clear();
        listalibros.getItems().addAll(lstforbook); // Agregamos los resultados de la selecciòn
    }
}
