module com.example.toolscansystem {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.toolscansystem to javafx.fxml;
    exports com.example.toolscansystem;
}