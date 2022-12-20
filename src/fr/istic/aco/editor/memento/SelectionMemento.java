package fr.istic.aco.editor.memento;

/**
 * Memento to change the cursor of the selection
 */
public class SelectionMemento implements Memento{

    private int beginIndex, endIndex;

    public SelectionMemento(int beginIndex, int endIndex){
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    /** Provides the index of the first character in the selection
     *
     * @return An int Value
     */
    public int getBeginIndex() {
        return beginIndex;
    }

    /** Provides the index of the first "virtual" character after the selection
     *
     * @return An int Value
     */
    public int getEndIndex() {
        return endIndex;
    }

}
