package ru.relex.kazino;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MainApp extends Application {
    private Stage primaryStage;
    private final File fileDir = new File("result.txt");

    int one;
    int two;
    int three;
    int counter = 0;
    int counterWin = 0;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        readFile();
        initWindow();
    }

    public void initWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartScreen.fxml"));
            AnchorPane pane = fxmlLoader.load();
            ControllerMainApp controller = fxmlLoader.getController();
            controller.setApp(this);
            controller.showStatisticsWin();

            Scene scene = new Scene(pane);
            primaryStage.setTitle("Оффлайн казино");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fileWrite() {
        try (FileWriter writer = new FileWriter(fileDir)) {
            writer.write(counter + "\n");
            writer.write(String.valueOf(counterWin));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile() {
        try (Scanner scanner = new Scanner(fileDir)) {
            counter = scanner.nextInt();
            counterWin = scanner.nextInt();
        } catch (IOException e) {
            fileWrite();
            readFile();
        }
    }

    public int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public int getStatistics() {
        if (counter != 0 && counterWin != 0) {
            return Math.round(((float) counterWin / counter) * 100);
        }
        return 0;
    }
}