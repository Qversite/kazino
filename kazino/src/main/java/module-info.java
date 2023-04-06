module ru.relex.winprogram {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.relex.kazino to javafx.fxml;
    exports ru.relex.kazino;
}