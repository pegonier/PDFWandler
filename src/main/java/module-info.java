module org.pegonier.pdfwandler {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    requires org.kordamp.bootstrapfx.core;
    requires itextpdf;
    requires jdk.jdi;
    requires org.apache.pdfbox;

    opens org.pegonier.pdfwandler to javafx.fxml;
    exports org.pegonier.pdfwandler;
}