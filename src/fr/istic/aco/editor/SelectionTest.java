package fr.istic.aco.editor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.Buffer;

import static org.junit.jupiter.api.Assertions.*;

class SelectionTest {

    private Selection selection;
    private String CH="ABCDEFGH";
    //Test Selectionner
    @BeforeEach
    public void setup() {
        StringBuilder PartieSelec = new StringBuilder(CH);
        selection = new SelectionImpl(PartieSelec);
    }

    @Test
    @DisplayName("Provides the index of the first character")
    public void getBeginIndex () {

        assertEquals(0,selection.getBeginIndex());

    }

    @Test
    @DisplayName("Provides the index of the last character")

    public void getEndIndex () {

        assertEquals(0,selection.getEndIndex());

    }

    @Test
    @DisplayName("Changes the value of the begin index of the selection")
    public void setBeginIndex () {

        assertThrows(IndexOutOfBoundsException.class,()->selection.setBeginIndex(CH.indexOf(CH.charAt(0))-1));

    }

    @Test
    @DisplayName("Changes the value of the end index of the selection")
    public void setEndIndex () {

        assertThrows(IndexOutOfBoundsException.class,()->selection.setEndIndex(CH.length()+1));

    }

}

