package fr.istic.aco.editor.test;
import fr.istic.aco.editor.command.*;
import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.enginecore.EngineImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.invoker.Client;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

    public class CommandTest {
        private Client invoker;
        private Engine engine;

        @BeforeEach
        void setUp() {
            invoker = new Client();
            engine= new EngineImpl();

            invoker.addCommand("Copy",new Copy(engine));
            invoker.addCommand("Cut",new Cut(engine));
            invoker.addCommand("Delete",new Delete(engine));
            invoker.addCommand("Paste",new Paste(engine));
            invoker.addCommand("Insert",new Insert(engine,invoker));
            invoker.addCommand("MoveSelection",new MoveSelection(engine,invoker));


        }

        @Test
        @DisplayName("AAAAAA")
        void TestInsert() {
            String STR1=String.format("Insert%nExit%n");
            InputStream targetStream1= new ByteArrayInputStream(STR1.getBytes());
            invoker.setReadStream(targetStream1);

            invoker.setText("ABC");
            invoker.executeCommand();

            String STR2=String.format("Insert%nExit%n");
            InputStream targetStream2= new ByteArrayInputStream(STR2.getBytes());
            invoker.setReadStream(targetStream2);

            invoker.setText("DEFG");
            invoker.executeCommand();

            assertEquals("ABCDEFG",engine.getBufferContents().toString());
        }

        @Test
        @DisplayName("AAAAAA")
        void TestDelete() {
            invoker.executeCommand();

            invoker.setText("ABCDEFGHT");
            invoker.executeCommand("Insert");
            invoker.setBeginIndex(0);
            invoker.setEndIndex(3);
            invoker.executeCommand("MoveSelection");
            invoker.executeCommand("Delete");
            assertEquals("DEFGHT",engine.getBufferContents().toString());
        }

        @Test
        @DisplayName("AAAAAA")
        void TestCopy() {
            invoker.setText("ABCDEFGHT");
            String STR1=String.format("Insert%nExit%n");
            InputStream targetStream1= new ByteArrayInputStream(STR1.getBytes());
            invoker.setReadStream(targetStream1);

            invoker.executeCommand();

            invoker.setBeginIndex(3);
            invoker.setEndIndex(6);
            String STR2=String.format("Select%nExit%n");
            InputStream targetStream2= new ByteArrayInputStream(STR2.getBytes());
            invoker.setReadStream(targetStream2);

            invoker.executeCommand();

            String STR3=String.format("Copy%nExit%n");
            InputStream targetStream3= new ByteArrayInputStream(STR3.getBytes());
            invoker.setReadStream(targetStream3);

            invoker.executeCommand();
            assertEquals("DEF",engine.getClipboardContents());
        }

        @Test
        @DisplayName("AAAAAA")
        void TestCut() {
            invoker.setText("ABCDEFGHT");
            invoker.executeCommand("Insert");
            invoker.setBeginIndex(3);
            invoker.setEndIndex(6);
            invoker.executeCommand("MoveSelection");
            invoker.executeCommand("Cut");
            assertEquals("DEF",engine.getClipboardContents());
            assertEquals("ABCGHT",engine.getClipboardContents());

        }
        @Test
        @DisplayName("AAAAAA")
        void TestPaste() {
            invoker.setText("ABCDEFGHT");
            invoker.executeCommand("Insert");
            invoker.setBeginIndex(3);
            invoker.setEndIndex(6);
            invoker.executeCommand("MoveSelection");
            invoker.executeCommand("Cut");
            assertEquals("DEF",engine.getClipboardContents());
            assertEquals("ABCGHT",engine.getBufferContents().toString());
            invoker.setBeginIndex(5);
            invoker.setEndIndex(8);
            invoker.executeCommand("Paste");
            assertEquals("ABCDEFGHT",engine.getBufferContents().toString());

        }

    }

