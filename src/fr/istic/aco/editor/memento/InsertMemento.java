package fr.istic.aco.editor.memento;

/**
 * Memento to store a text in order to insert text
 */
public class InsertMemento implements Memento{

    private String text;

    /** Change the current text content by the given param
     *
     * @param text A String value
     */
    public InsertMemento(String text) {
        this.text = text;
    }

    /** Provide the saved text in the memento
     *
     * @return The saved text
     */
    public String getText() {
        return this.text;
    }
}
