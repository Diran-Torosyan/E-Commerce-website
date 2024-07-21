package com.example.clothingstore.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.Map;

public class HomePageController {

    @FXML
    private TextField searchBar;

    @FXML
    private Button filterButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button cartButton;

    @FXML
    private GridPane itemsContainer;

    private Map<String, Image> items;

    @FXML
    private void initialize() {
        items = new HashMap<>();
        items.put("Black Flannel", new Image(getClass().getResourceAsStream("/images/black_flannel.jpg")));
        items.put("Blue Flannel", new Image(getClass().getResourceAsStream("/images/blue_flannel.jpg")));
        items.put("Green Flannel", new Image(getClass().getResourceAsStream("/images/green_flannel.jpg")));
        items.put("Red Flannel", new Image(getClass().getResourceAsStream("/images/red_flannel.jpg")));
        items.put("Yellow Flannel", new Image(getClass().getResourceAsStream("/images/yellow_flannel.jpg")));

        displayItems(items);
    }

    @FXML
    private void handleSearch() {
        String searchText = searchBar.getText().toLowerCase();
        Map<String, Image> filteredItems = new HashMap<>();
        for (Map.Entry<String, Image> entry : items.entrySet()) {
            if (entry.getKey().toLowerCase().contains(searchText)) {
                filteredItems.put(entry.getKey(), entry.getValue());
            }
        }
        displayItems(filteredItems);
    }

    private void displayItems(Map<String, Image> itemsToDisplay) {
        itemsContainer.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Map.Entry<String, Image> entry : itemsToDisplay.entrySet()) {
            VBox itemBox = new VBox();
            ImageView imageView = new ImageView(entry.getValue());
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);
            itemBox.getChildren().add(imageView);

            itemsContainer.add(itemBox, column, row);

            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }
    }

    @FXML
    private void handleFilter() {
        System.out.println("Filter button clicked");
        // Add filter functionality here
    }

    @FXML
    private void handleAccount() {
        System.out.println("Account button clicked");
        // Add account functionality here
    }

    @FXML
    private void handleCart() {
        System.out.println("Cart button clicked");
        // Add cart functionality here
    }
}
