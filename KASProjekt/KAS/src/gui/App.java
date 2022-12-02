package gui;

import javafx.application.Application;
import storage.Storage;

public class App {
    public static void main(String[] args) {
        Storage.initContent();
        Application.launch(StartWindow.class);

    }
}
