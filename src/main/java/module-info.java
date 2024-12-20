module org.pegonier.pdfwandler {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires itextpdf;

    opens org.pegonier.pdfwandler to javafx.fxml;
    exports org.pegonier.pdfwandler;
}