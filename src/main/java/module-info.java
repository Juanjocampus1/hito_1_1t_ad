module com.empresa.hito_1_ad_juanjose_acebedo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    opens com.empresa.hito_1_ad_juanjose_acebedo to javafx.fxml;
    exports com.empresa.hito_1_ad_juanjose_acebedo;
    exports com.empresa.hito_1_ad_juanjose_acebedo.controller;
    opens com.empresa.hito_1_ad_juanjose_acebedo.controller to javafx.fxml;
    exports com.empresa.hito_1_ad_juanjose_acebedo.Repository;
    opens com.empresa.hito_1_ad_juanjose_acebedo.Repository to javafx.fxml;
    exports com.empresa.hito_1_ad_juanjose_acebedo.entities;
    opens com.empresa.hito_1_ad_juanjose_acebedo.entities to javafx.fxml;
}