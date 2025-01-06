module org.pegonier.pdfwandler {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.kordamp.bootstrapfx.core;
    requires itextpdf;
    requires jdk.jdi;

    opens org.pegonier.pdfwandler to javafx.fxml;
    exports org.pegonier.pdfwandler;
}