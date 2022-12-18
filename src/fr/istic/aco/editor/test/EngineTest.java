package fr.istic.aco.editor.test;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.enginecore.EngineImpl;
import fr.istic.aco.editor.enginecore.Selection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private StringBuffer TEST_BUFFER;
    private Engine engine;

    @BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }

    @Test
    @DisplayName("Check getBufferContents method ")
    void getBufferContents() {
        assertEquals("", engine.getBufferContents());
        engine.insert("ABCDEF");
        assertEquals("ABCDEF", engine.getBufferContents());
    }
    @Test
    @DisplayName("Check getClipboardContents method")
    void getClipboardContents() {
        engine.insert("ABCDEF");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.copySelectedText();
        assertEquals("ABC", engine.getClipboardContents());
    }
    @Test
    @DisplayName("Check cutSelectedText")
    void cutSelectedText() {
        engine.insert("ABCDEF");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.cutSelectedText();
        assertEquals("ABC", engine.getClipboardContents());
        assertEquals("DEF", engine.getBufferContents());
        assertEquals(selection.getEndIndex(),selection.getBeginIndex());
    }
    @Test
    @DisplayName("copySelectedText method")
    void copySelectedText() {
        engine.insert("ABCDEF");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.copySelectedText();
        assertEquals("ABC", engine.getClipboardContents());
    }
    @Test
    @DisplayName("Check pasteClipboard method")

    void pasteClipboard() {
        engine.insert("ABCDEFGHIJ");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.copySelectedText();

        assertEquals("ABC", engine.getClipboardContents());

        selection.setBeginIndex(4);
        selection.setEndIndex(7);
        engine.pasteClipboard();

        assertEquals("ABCDABCHIJ", engine.getBufferContents());

    }

    @Test
    @DisplayName("Check Insert method")
    void Insert() {
        int oldBeginIndex=engine.getSelection().getBeginIndex();
        String str = "ABCDEF";
        engine.insert(str);
        assertEquals(engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex()); //On a plus de séléction
        assertEquals(engine.getSelection().getBeginIndex(),oldBeginIndex+ str.length());
        assertEquals("ABCDEF", engine.getBufferContents());
    }
    @Test
    @DisplayName("Check Insert method : an empty String insertion")
    void InsertEmptyString()
    { this.engine.insert("ABCDEF");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        assertEquals(0, engine.getClipboardContents().length());
        this.engine.insert("");
    }

    @Test
    @DisplayName("Check Insert method : with cut and insert")
    void InsertOnAnotherSelectionWithCut()
    { this.engine.insert("ABCDEFHGIJK");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.cutSelectedText();
        assertEquals("ABC", engine.getClipboardContents());
        assertEquals("DEFHGIJK", engine.getBufferContents());
        selection.setBeginIndex(2);
        selection.setEndIndex(5);
        engine.insert(engine.getClipboardContents());
        //engine.pasteClipboard();
        assertEquals(3, engine.getClipboardContents().length());
        assertEquals("DEABCIJK", engine.getBufferContents());
    }

    @Test
    @DisplayName("Check Insert method : with copy and insert")
    void InsertOnAnotherSelectionWithCopy()
    { this.engine.insert("ABCDEFHGIJK");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.copySelectedText();
        assertEquals("ABC", engine.getClipboardContents());
        assertEquals("ABCDEFHGIJK", engine.getBufferContents());
        selection.setBeginIndex(5);
        selection.setEndIndex(8);
        engine.insert(engine.getClipboardContents());
        assertEquals(3, engine.getClipboardContents().length());
        assertEquals("ABCDEABCIJK", engine.getBufferContents());
    }
    @Test
    @DisplayName("Check Insert method : at the END")
    void InsertAtTheEnd()
    {  this.engine.insert("ABCDEFHGIJK");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(selection.getBufferEndIndex());
        selection.setEndIndex(selection.getBufferEndIndex());
        engine.insert("AAAA");
        assertEquals("ABCDEFHGIJK"+"AAAA", engine.getBufferContents());
    }
    @Test
    @DisplayName("Check Delete method : Delete all")
    void InsertAndDeleteAll()
    {   this.engine.insert("ABCDEFHGIJK");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(11);
        engine.delete();
        assertEquals("", engine.getBufferContents());
    }

    @Test
    @DisplayName("Check Delete method : Delete the selected part")
    void InsertAndDeleteTheSelectedPart()
    {   this.engine.insert("ABCDEFHGIJK");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        this.engine.delete();
        assertEquals("DEFHGIJK", engine.getBufferContents());
        selection.setBeginIndex(2);
        selection.setEndIndex(5);
        this.engine.delete();
        assertEquals(0, engine.getClipboardContents().length());
        assertEquals("DEIJK", engine.getBufferContents());
    }
    @Test
    @DisplayName("endIndex cannot be bigger than bufferEndIndex")
    void selectionEndIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            engine.getSelection().setBeginIndex(0);
            engine.getSelection().setEndIndex(1);
        });
    }
    @Test
    @DisplayName("endIndex cannot be lower than BeginIndex")
    void endIndexMustNotBeLowerThanBeginIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            engine.getSelection().setBeginIndex(2);
            engine.getSelection().setEndIndex(1);
        });
    }
    @Test
    @DisplayName("beginIndex can not be negative")
    void beginIndexMustNotBeNegative() {
        Selection selection = engine.getSelection();
        Throwable t= assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(-4), "The index");
        assertEquals("Impossible to set begin index", t.getMessage());
        assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(2), "beginIndex must be lower than endIndex");
    }

    @Test
    @DisplayName("Buffer length must be equal to buffer endIndex")
    void bufferLengthMustBeEqualBufferEndIndex() {
        engine.insert("ABCDEF");
        Selection selection = engine.getSelection();
        assertEquals(engine.getBufferContents().length(),selection.getBufferEndIndex());
    }
    @Test
    @DisplayName("Begin and end must be 0 after initialization")
    void beginAndEndMustBeZeroAfterInitialization() {
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }


}