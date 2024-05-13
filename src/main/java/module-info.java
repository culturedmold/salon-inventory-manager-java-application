module com.example.saloninventorymanager {
    requires javafx.controls;
    requires java.sql;


    opens com.example.saloninventorymanager to javafx.base;
    exports com.example.saloninventorymanager;
    exports com.example.saloninventorymanager.util;
    opens com.example.saloninventorymanager.util to javafx.base;
}