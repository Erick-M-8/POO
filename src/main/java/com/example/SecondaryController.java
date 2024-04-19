package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SecondaryController {
    @FXML
    TextField txt1, txt2, txt3, txt4, txt5;
    @FXML
    ListView<String> listalibros;

    @FXML
    public void Button2C2_click(){
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
        String titulo = txt1.getText();
        String autor = txt3.getText();
        String editorial = txt3.getText();
        int año= Integer.parseInt(txt4.getText());
        String  isbn  = txt4.getText();
        String complementos = txt5.getText();
  
        // Insercion de datos
        try {
            String query = "INSERT INTO catalogo (titulo, autor, editorial, año, isbn, complementos) Values ('"+titulo+"', '"+autor+"', '"+editorial+"', '"+año+"', '"+isbn+"', '"+complementos+"');";
            Statement stmt = cnx.createStatement();
            stmt.execute(query);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Mensaje del Sistema");
            alert.setHeaderText(null);
            alert.setContentText("El libro se agrego correctamente");
            alert.showAndWait();

        }catch (Exception excepcion) { // Si se produce una excepciòn
            System.out.println("Error: " + excepcion.getMessage());
            return;
        }
    }
}