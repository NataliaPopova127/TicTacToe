package com.example.tictactoe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.Optional;

public class MainController {
    public VBox mainVBox;
    public Label scoreFirstPlayer;
    public Label scoreSecondPlayer;
    public Label playerX;
    public Label playerO;
    public GridPane mainGrid;
    public int scoreFirst;
    public int scoreSecond;


    @FXML
    private void initialize(){
        // Создаем цвет фона
        BackgroundFill backgroundFill = new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        mainVBox.setBackground(background);

        for(int i = 0; i <= 8; i++){
            ((Button) mainGrid.getChildren().get(i)).setBackground(background);
            ((Button) mainGrid.getChildren().get(i)).setStyle("-fx-text-fill: DARKVIOLET;");
        }

        scoreFirstPlayer.setFont(new Font(25));
        scoreSecondPlayer.setFont(new Font(25));

        playerX.setTextFill(Color.DARKVIOLET);
        playerO.setTextFill(Color.BLACK);

        scoreFirst = 0;
        scoreSecond = 0;
    }

    @FXML
    private void onGrindCellc0r0Click(){
        changeTextInCell(((Button) mainGrid.getChildren().get(0)));
    }
    @FXML
    private void onGrindCellc1r0Click(){
        changeTextInCell(((Button) mainGrid.getChildren().get(1)));
    }
    @FXML
    private void onGrindCellc2r0Click(){
        changeTextInCell(((Button) mainGrid.getChildren().get(2)));
    }
    @FXML
    private void onGrindCellc0r1Click(){
        changeTextInCell(((Button) mainGrid.getChildren().get(3)));
    }
    @FXML
    private void onGrindCellc1r1Click(){
        changeTextInCell(((Button) mainGrid.getChildren().get(4)));
    }
    @FXML
    private void onGrindCellc2r1Click(){
        changeTextInCell(((Button) mainGrid.getChildren().get(5)));
    }
    @FXML
    private void onGrindCellc0r2Click(){
        changeTextInCell(((Button) mainGrid.getChildren().get(6)));
    }
    @FXML
    private void onGrindCellc1r2Click(){
        changeTextInCell(((Button) mainGrid.getChildren().get(7)));
    }
    @FXML
    private void onGrindCellc2r2Click(){
        changeTextInCell(((Button) mainGrid.getChildren().get(8)));
    }

    private void changeTextInCell(Button cell){
        if(playerX.getTextFill() == Color.DARKVIOLET){
            cell.setText("X");
            playerX.setTextFill(Color.BLACK);
            playerO.setTextFill(Color.DARKVIOLET);
        }
        else if(playerO.getTextFill() == Color.DARKVIOLET){
            cell.setText("O");
            playerX.setTextFill(Color.DARKVIOLET);
            playerO.setTextFill(Color.BLACK);
        }
        cell.setDisable(true);
        checkWin();
    }

    private void checkWin(){
        if(isWin("X")){
            scoreFirst += 1;
            scoreFirstPlayer.setText(String.valueOf(scoreFirst));
            mainGrid.setDisable(true);

//            Image customIcon = new Image("/resources/images/tictactoe.png"); // Замените на реальный путь
//            ImageView iconView = new ImageView(customIcon);
//            iconView.setFitWidth(48); // Задайте нужный размер
//            iconView.setFitHeight(48);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ПОБЕДА!");
            alert.setContentText("Поздравляем!\nВыиграл Х\nСчет: " + scoreFirst + ":" + scoreSecond + "\nПродолжить?");
            alert.setHeaderText(null);
           // alert.getDialogPane().setGraphic(iconView);

            ButtonType buttonYes = new ButtonType("Да");
            ButtonType buttonNo = new ButtonType("Нет");
            alert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {
                cleanGridPane();
            } else {
                Platform.exit();
            }
        }
        if(isWin("O")){
            scoreSecond += 1;
            scoreSecondPlayer.setText(String.valueOf(scoreSecond));
            mainGrid.setDisable(true);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ПОБЕДА!");
            alert.setContentText("Поздравляем!\nВыиграл O\nСчет: " + scoreFirst + ":" + scoreSecond + "\nПродолжить?");
            alert.setHeaderText(null);

            ButtonType buttonYes = new ButtonType("Да");
            ButtonType buttonNo = new ButtonType("Нет");
            alert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {
                cleanGridPane();
            } else {
                Platform.exit();
            }
        }
    }
    private boolean isWin(String text){
        //0 1 2
        //3 4 5
        //6 7 8

        //012
        if(((Button) mainGrid.getChildren().get(0)).getText() == text
                && ((Button) mainGrid.getChildren().get(1)).getText() == text
                && ((Button) mainGrid.getChildren().get(2)).getText() == text){
            return true;
        }
        //345
        else if(((Button) mainGrid.getChildren().get(3)).getText() == text
                && ((Button) mainGrid.getChildren().get(4)).getText() == text
                && ((Button) mainGrid.getChildren().get(5)).getText() == text){
            return true;
        }
        //678
        else if(((Button) mainGrid.getChildren().get(6)).getText() == text
                && ((Button) mainGrid.getChildren().get(7)).getText() == text
                && ((Button) mainGrid.getChildren().get(8)).getText() == text){
            return true;
        }
        //048
        else if(((Button) mainGrid.getChildren().get(0)).getText() == text
                && ((Button) mainGrid.getChildren().get(4)).getText() == text
                && ((Button) mainGrid.getChildren().get(8)).getText() == text){
            return true;
        }
        //246
        else if(((Button) mainGrid.getChildren().get(2)).getText() == text
                && ((Button) mainGrid.getChildren().get(4)).getText() == text
                && ((Button) mainGrid.getChildren().get(6)).getText() == text){
            return true;
        }
        //036
        else if(((Button) mainGrid.getChildren().get(0)).getText() == text
                && ((Button) mainGrid.getChildren().get(3)).getText() == text
                && ((Button) mainGrid.getChildren().get(6)).getText() == text){
            return true;
        }
        //147
        else if(((Button) mainGrid.getChildren().get(1)).getText() == text
                && ((Button) mainGrid.getChildren().get(4)).getText() == text
                && ((Button) mainGrid.getChildren().get(7)).getText() == text){
            return true;
        }
        //258
        else if(((Button) mainGrid.getChildren().get(2)).getText() == text
                && ((Button) mainGrid.getChildren().get(5)).getText() == text
                && ((Button) mainGrid.getChildren().get(8)).getText() == text){
            return true;
        }
        return false;
    }
    private void cleanGridPane(){
        mainGrid.setDisable(false);
        for(int i = 0; i <= 8; i++){
            ((Button) mainGrid.getChildren().get(i)).setText("");
            ((Button) mainGrid.getChildren().get(i)).setDisable(false);
        }
    }

}