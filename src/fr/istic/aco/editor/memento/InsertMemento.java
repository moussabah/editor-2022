package fr.istic.aco.editor.memento;

import java.io.BufferedReader;
import java.io.IOException;

public class InsertMemento implements Memento{

    private String text = "";
    private BufferedReader bufferedReader;

    public String getText() {
        try {
            this.text = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
