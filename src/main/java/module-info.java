module org.entdes {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.entdes to javafx.fxml;
    exports org.entdes;
}
