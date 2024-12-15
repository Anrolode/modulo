package com.minimizing.system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.minimizing.system.util.HibernateUtil;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args); // Inicia la aplicación JavaFX
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestión de Productos");

        // Crear layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Crear campos de texto y etiquetas
        Label nameLabel = new Label("Nombre:");
        GridPane.setConstraints(nameLabel, 0, 0);

        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Label quantityLabel = new Label("Cantidad:");
        GridPane.setConstraints(quantityLabel, 0, 1);

        TextField quantityInput = new TextField();
        GridPane.setConstraints(quantityInput, 1, 1);

        Label priceLabel = new Label("Precio:");
        GridPane.setConstraints(priceLabel, 0, 2);

        TextField priceInput = new TextField();
        GridPane.setConstraints(priceInput, 1, 2);

        // Botón para guardar
        Button saveButton = new Button("Guardar Producto");
        GridPane.setConstraints(saveButton, 1, 3);

        // Acción del botón
        saveButton.setOnAction(e -> {
            String nombre = nameInput.getText();
            int cantidad;
            double precio;

            try {
                cantidad = Integer.parseInt(quantityInput.getText());
                precio = Double.parseDouble(priceInput.getText());

                guardarProducto(nombre, cantidad, precio);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Producto guardado exitosamente!");
                alert.showAndWait();

                // Limpiar campos
                nameInput.clear();
                quantityInput.clear();
                priceInput.clear();

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, ingresa valores válidos.");
                alert.showAndWait();
            }
        });

        // Añadir todo al layout
        grid.getChildren().addAll(nameLabel, nameInput, quantityLabel, quantityInput, priceLabel, priceInput, saveButton);

        // Crear escena
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void guardarProducto(String nombre, int cantidad, double precio) {
        // Crear una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Crear un nuevo producto
            Producto producto = new Producto(nombre, cantidad, precio);

            // Guardar el producto en la base de datos
            session.save(producto);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
