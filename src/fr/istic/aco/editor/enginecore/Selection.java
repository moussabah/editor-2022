package fr.istic.aco.editor.enginecore;

/**
 * Provides access to selection control operations
 *
 * @author plouzeau
 * @version 1.0
 */
public interface Selection {

    /**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return An int value
     */
     int getBeginIndex();

    /**
     * Provides the index of the first character
     * after the last character designated
     * by the selection.
     *
     * @return the last int on the selection
     */
    int getEndIndex();

    /**
     * Provides the index of the first character in the buffer
     *
     * @return An int value
     */
    int getBufferBeginIndex();

    /**
     * Provides the index of the first "virtual" character
     * after the end of the buffer
     *
     * @return the post end buffer index
     */
    int getBufferEndIndex();

    /**
     * Changes the value of the beginning index of the selection
     *
     * @param beginIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    void setBeginIndex(int beginIndex);

    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    void setEndIndex(int endIndex);
}
