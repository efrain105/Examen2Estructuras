module org.cardona.estructuras.listascirculardoble {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.cardona.estructuras.listascirculardoble.controller to javafx.fxml;
    opens org.cardona.estructuras.listascirculardoble.modelo to javafx.base;
    exports org.cardona.estructuras.listascirculardoble.stages to javafx.graphics; // Added this line
    exports org.cardona.estructuras.listascirculardoble;

}