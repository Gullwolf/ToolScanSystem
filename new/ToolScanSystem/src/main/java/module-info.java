module com.example.toolscansystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;
    requires java.desktop;
    requires barcode4j;


    opens com.example.toolscansystem to javafx.fxml;
    exports com.example.toolscansystem;
}