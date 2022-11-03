package fr.istic.aco.editor;

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
        engine.insert("ABCDEF");
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        engine.copySelectedText();
        engine.pasteClipboard();
        assertEquals("ABC", engine.getClipboardContents());
    }
}
