/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikipuzzlegui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author pe79
 */
public class FutoshikiPuzzleGUI extends Application {
    
    private Stage primaryStage;
    private FutoshikiPuzzle2 futoshikiGame;
    private int difficulty;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        gameMenu();
    }

    private void gameMenu() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Label title = new Label("Game Difficulty:");
        Button easy = new Button("Easy");
        easy.setOnAction(e -> {
            difficulty = 0;
            game();
        });
        Button medium = new Button("Medium");
        medium.setOnAction(e -> {
            difficulty = 1;
            game();
        });
        Button hard = new Button("Hard");
        hard.setOnAction(e -> {
            difficulty = 2;
            game();
        });
        
        Button extreme = new Button("Extreme");
        hard.setOnAction(e -> {
            difficulty = 3;
            game();
        });

        root.getChildren().addAll(title, easy, medium, hard, extreme);
        Scene scene = new Scene(root, 300, 280);

        primaryStage.setTitle("Futoshiki Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void game() {
        BorderPane bp = new BorderPane();

        HBox buttonBox = new HBox();
        Button back = new Button("Back");
        Button newG = new Button("New Game");
        //Button clear = new Button("Clear");
        buttonBox.getChildren().addAll(back, newG);
        bp.setBottom(buttonBox);
        buttonBox.setStyle("-fx-alignment:center");
        
        newGame(bp);

        back.setOnAction(e -> {
            gameMenu();
        });
        
        newG.setOnAction(e -> {
            newGame(bp);            
        });

        Scene scene = new Scene(bp, 600, 590);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void newGame(BorderPane bp) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        bp.setCenter(root);
        root.setHgap(10);
        root.setVgap(10); 

        switch (difficulty) {
            
            case 0:
                futoshikiGame = new FutoshikiPuzzle2(4);
                futoshikiGame.fillPuzzle(2, 2); // 6,4
                break;
            case 1:
                futoshikiGame = new FutoshikiPuzzle2(6);
                futoshikiGame.fillPuzzle(2, 2); // 4,10
                break;
            case 2:
                futoshikiGame = new FutoshikiPuzzle2(8);
                futoshikiGame.fillPuzzle(2, 2); // 7,15
                break;
                
            case 3:
                futoshikiGame = new FutoshikiPuzzle2(10);
                futoshikiGame.fillPuzzle(2, 2); // 9,18
                break;
        }

        for (int i = 0; i < futoshikiGame.gridSize; i++) {
            for (int j = 0; j < futoshikiGame.gridSize; j++) {
                TextBox tf = new TextBox(futoshikiGame.getGrid(i, j).getSquare());
                root.add(tf, i * 2, j * 2);
            }
        }

        for (int i = 0; i < futoshikiGame.gridSize - 1; i++) {
            for (int j = 0; j < futoshikiGame.gridSize - 1; j++) {
                Label rowCons = new Label(futoshikiGame.getRowConstraints(i, j));
                root.add(rowCons, i * 2 + 1, j * 2);
            }
        }

        for (int i = 0; i < futoshikiGame.gridSize - 1; i++) {
            for (int j = 0; j < futoshikiGame.gridSize - 1; j++) {
                Label colCons = new Label(futoshikiGame.getColumnConstraints(i, j));
                GridPane.setHalignment(colCons, HPos.CENTER);
                root.add(colCons, i * 2, j * 2 + 1);
            }
        }
    }
}