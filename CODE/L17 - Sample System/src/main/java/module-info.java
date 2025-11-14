module edu.westga.cs1302.coursegui {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens edu.westga.cs1302.coursegui.view to javafx.fxml;
    exports edu.westga.cs1302.coursegui;
}
