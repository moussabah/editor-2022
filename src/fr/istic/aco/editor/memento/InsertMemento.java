package fr.istic.aco.editor.memento;
public class InsertMemento implements Memento{

    private String text;

    public InsertMemento(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
