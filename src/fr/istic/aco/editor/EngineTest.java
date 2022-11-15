package fr.istic.aco.editor;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Engine engine;
    private String TEST_STRING = "abc";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl(TEST_STRING);
    }

    @org.junit.jupiter.api.Test
    void getSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
    }

    @org.junit.jupiter.api.Test
    void getBufferContents() {
        assertEquals(engine.getBufferContents(),"abc");
    }

    @org.junit.jupiter.api.Test
    void insertTextInEngine(){
        engine.insert("def");
        assertEquals(engine.getBufferContents(),"def");
    }
    @org.junit.jupiter.api.Test
    void insertTextInEngineFailed(){
        engine.insert("gh");
        assertFalse(engine.getBufferContents().equals("ijk"));
    }


    @org.junit.jupiter.api.Test
    void getClipboardContents() {
    }

    @org.junit.jupiter.api.Test
    void cutSelectedText() {
    }

    @org.junit.jupiter.api.Test
    void copySelectedText() {
    }

    @org.junit.jupiter.api.Test
    void pasteClipboard() {
    }
}
