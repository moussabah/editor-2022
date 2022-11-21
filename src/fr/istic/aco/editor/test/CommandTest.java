package fr.istic.aco.editor.test;

import fr.istic.aco.editor.enginecore.*;
import fr.istic.aco.editor.invoker.Client;
import fr.istic.aco.editor.invoker.Invoker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    private Invoker invoker;
    private Engine receiver;

    @BeforeEach
    void setUp() { //Dans le setUp des test on utilise l'implémentation des test ou l'interface ???
        invoker = new Client();
        receiver = new EngineImpl();
    }

    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = receiver.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",receiver.getBufferContents());
    }

    @Test
    @DisplayName("Check getBufferContents method ")
    void getBufferContents() {
        assertEquals("", receiver.getBufferContents());
        receiver.insert("ABCDEF");
        assertEquals("ABCDEF", receiver.getBufferContents());
    }
    @Test
    @DisplayName("Check getClipboardContents method")
    void getClipboardContents() {
        receiver.insert("ABCDEF");
        Selection selection = receiver.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(3);
        receiver.copySelectedText();
        assertEquals("ABC", receiver.getClipboardContents());
    }


}
