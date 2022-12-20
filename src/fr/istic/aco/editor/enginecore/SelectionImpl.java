package fr.istic.aco.editor.enginecore;

/**
 * Implement the Selection interface
 */
public class SelectionImpl implements Selection {

    private StringBuilder buffer;

    //The index of the first element of the selection
    private Integer beginIndex;

    //The index of the first element of the selection
    private Integer endIndex;

    //Index of the buffer beginning
    private Integer buffer_begin_index;

    public SelectionImpl(StringBuilder buffer){
        this.buffer = buffer;
        this.beginIndex = 0;
        this.endIndex = 0;
        this.buffer_begin_index=0;
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
        return this.buffer_begin_index;
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
     * @param beginIndex@throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setBeginIndex(int beginIndex) {
        if(beginIndex >= this.getBufferBeginIndex() && beginIndex <= this.getBufferEndIndex() ){
            this.beginIndex = beginIndex;
        }
        else{
            throw  new IndexOutOfBoundsException("Impossible to set begin index");
        }
    }

    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setEndIndex(int endIndex) {
        if (endIndex >= this.getBufferBeginIndex() && endIndex <= this.getBufferEndIndex() ){
            if (endIndex < this.getBeginIndex()){
                this.endIndex = getBeginIndex();
                setBeginIndex(endIndex);
            }
            else {
                this.endIndex = endIndex;
            }
        }
        else{
            throw  new IndexOutOfBoundsException("Impossible to set end index");
        }
    }
}
