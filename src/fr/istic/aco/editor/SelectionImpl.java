package fr.istic.aco.editor;

public class SelectionImpl implements Selection {

    StringBuilder buffer;
    Integer beginIndex;
    Integer endIndex;
    Integer buffer_begin_integer;

    public SelectionImpl(StringBuilder buffer){
        this.buffer = buffer;
    }
    /**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return the first character designated by the selection.
     */
    @Override
    public int getBeginIndex() {
        return this.beginIndex;
    }

    /**
     * Provides the index of the first character
     * after the last character designated
     * by the selection.
     *
     * @return the end index
     */
    @Override
    public int getEndIndex() {
        return this.endIndex;
    }

    /**
     * Provides the index of the first character in the buffer
     *
     * @return the buffer's begin index
     */
    @Override
    public int getBufferBeginIndex() {
        return this.buffer_begin_integer;
    }

    /**
     * Provides the index of the first "virtual" character
     * after the end of the buffer
     *
     * @return the post end buffer index
     */
    @Override
    public int getBufferEndIndex() {
        return this.buffer.length();
    }

    /**
     * Changes the value of the begin index of the selection
     *
     * @param beginIndex@throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setBeginIndex(int beginIndex) {

        this.beginIndex = beginIndex;
    }

    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex@throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
