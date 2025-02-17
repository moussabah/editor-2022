package fr.istic.aco.editor.enginecore;

/**
 * Implement the Engine interface
 */
public class EngineImpl implements Engine {

    // The buffer
    private StringBuilder buffer = new StringBuilder();
    private String clipboard="";
    private Selection selection = new SelectionImpl(buffer);

    /**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    @Override
    public Selection getSelection() {
        return this.selection;
    }

    /**
     * Provides the whole contents of the buffer, as a string
     *
     * @return a copy of the buffer's contents
     */
    @Override
    public String getBufferContents() {
        return this.buffer.toString();
    }

    /**
     * Provides the clipboard contents
     *
     * @return a copy of the clipboard's contents
     */
    @Override
    public String getClipboardContents() {
        return clipboard.toString();
    }

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    @Override
    public void cutSelectedText() {
        this.copySelectedText();
        this.delete();
    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        int begin = getSelection().getBeginIndex();
        int end = getSelection().getEndIndex();
        clipboard = buffer.substring(begin, end);
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {
        this.insert(clipboard);
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
        int begin = getSelection().getBeginIndex();
        int end = getSelection().getEndIndex();
        buffer.replace(begin, end, s);  //Pb: Si y a rien a cet endroit.
        selection.setEndIndex(begin+s.length());
        selection.setBeginIndex(selection.getEndIndex());
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
        int begin = getSelection().getBeginIndex();
        int end = getSelection().getEndIndex();
        buffer.delete(begin, end);
        selection.setEndIndex(selection.getBeginIndex());
    }
}
