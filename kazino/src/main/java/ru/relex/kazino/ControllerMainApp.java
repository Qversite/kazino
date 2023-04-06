package ru.relex.kazino;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ControllerMainApp {
    private MainApp app;

    public void setApp(MainApp app) {
        this.app = app;
    }

    @FXML
    private TextField oneNumber;

    @FXML
    private TextField twoNumber;
    @FXML
    private TextField threeNumber;

    @FXML
    private ImageView image;

    @FXML
    private Label statistics;

    @FXML
    private void initialize() {
        image.setVisible(false);
    }

    @FXML
    void drumScroll() {
        image.setVisible(false);
        app.one = app.randomInt(0, 9);
        app.two = app.randomInt(0, 9);
        app.three = app.randomInt(0, 9);

        oneNumber.setText(String.valueOf(app.one));
        twoNumber.setText(String.valueOf(app.two));
        threeNumber.setText(String.valueOf(app.three));

        if (app.one == 7 || app.two == 7 || app.three == 7) {
            image.setVisible(true);
            app.counterWin++;
        }
        app.counter++;
        showStatisticsWin();
    }

    public void showStatisticsWin() {
        statistics.setText("Процент выигрыша от всех игр - " + app.getStatistics() + "%");
    }

    @FXML
    void exitProgram() {
        app.fileWrite();
        System.exit(0);
    }
}