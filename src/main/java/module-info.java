module org.cardona.estructuras.listascirculardoble {
    requires javafx.controls;
    requires javafx.fxml;

    // Abrir paquetes específicos a javafx.fxml
    opens org.cardona.estructuras.controller to javafx.fxml;
    opens org.cardona.estructuras.controller.listacircularc to javafx.fxml;
    opens org.cardona.estructuras.controller.listasSimplesC to javafx.fxml;
    opens org.cardona.estructuras.controller.listasDoblesC to javafx.fxml;
    opens org.cardona.estructuras.controller.pilasC to javafx.fxml;
    opens org.cardona.estructuras.controller.queueC to javafx.fxml;
    opens org.cardona.estructuras.controller.arrayC to javafx.fxml;



    opens org.cardona.estructuras.modelo to javafx.base;
    opens org.cardona.estructuras.modelo.listacircular to javafx.base;
    opens org.cardona.estructuras.modelo.listassimples to javafx.base;
    opens org.cardona.estructuras.modelo.listasdobles to javafx.base;
    opens org.cardona.estructuras.modelo.pila to javafx.base;
    opens org.cardona.estructuras.modelo.cola to javafx.base;
    opens org.cardona.estructuras.modelo.array.vehiculos to javafx.base;



    // Exportar los paquetes necesarios para gráficos y otras dependencias
    exports org.cardona.estructuras.stages.listacircular to javafx.graphics;
    exports org.cardona.estructuras.stages.listasSimplesS to javafx.graphics;
    exports org.cardona.estructuras.stages.listasDoblesS to javafx.graphics;
    exports org.cardona.estructuras.stages.pilasS to javafx.graphics;
    exports org.cardona.estructuras.stages.queueS to javafx.graphics;
    exports org.cardona.estructuras.stages.arrayS to javafx.graphics;



    exports org.cardona.estructuras.stages to javafx.graphics;
    exports org.cardona.estructuras;
    exports org.cardona.estructuras.tests;


}
